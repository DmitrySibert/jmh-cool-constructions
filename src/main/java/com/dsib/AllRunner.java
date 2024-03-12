package com.dsib;

import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.profile.Profiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.List;

public class AllRunner {

    private BenchmarkingCase[] benchmarkingCases = {
            new BenchmarkingCase("BubbleVsQuick"),
            new BenchmarkingCase("CodeVsRxCollectionMapComparison"),
            new BenchmarkingCase("ConcurrentMapsVsMapComparison"),
            new BenchmarkingCase("InvocationLevelSetupIssue"),
            new BenchmarkingCase("ListVsStreamCollectionMapComparison"),
            new BenchmarkingCase("MethodReferenceVsLambda"),
            new BenchmarkingCase("MonitorLockCost"),
            new BenchmarkingCase("OptionalVsIfNullComparison", GCProfiler.class),
            new BenchmarkingCase("ReturnVsThrowException")
    };

    private static final Integer WARMUP = 1;
    private static final Integer ITERATIONS = 1;
    private static final Integer FORKS = 1;

    public static void main(String[] args) {
//        runAllCases("com.dsib.cases");
        runCase("OptionalVsIfNullComparison", null, GCProfiler.class);
    }

    private static void runCase(String caseName, String outputFileName, Class<? extends Profiler> profiler) {
        try {
            OptionsBuilder optionsBuilder = new OptionsBuilder();
            optionsBuilder.include(caseName)
                    .warmupIterations(WARMUP)
                    .measurementIterations(ITERATIONS)
                    .forks(FORKS);
            if (outputFileName != null) {
                optionsBuilder.output(outputFileName);
            }
            if (profiler != null) {
                optionsBuilder.addProfiler(profiler);
            }
            new Runner(optionsBuilder.build()
            ).run();
        } catch (RunnerException e) {
            throw new RuntimeException(e);
        }
    }

    private static void runCase(String caseName, String outputFileName) {
        runCase(caseName, outputFileName, null);
    }

    private static void runAllCases(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        String casesRegex = packageName + ".[a-zA-z]*$";
        List<String> casesClassNames = reflections.getAllTypes().stream()
                .filter(s -> s.matches(casesRegex))
                .sorted()
                .toList();
        for (String caseName : casesClassNames) {
            runCase(caseName, caseName + ".txt");
        }
    }

    private static void runAllCases(BenchmarkingCase[] benchmarkingCases) {
        for (BenchmarkingCase benchmarkingCase : benchmarkingCases) {
            runCase(
                    benchmarkingCase.getClassName(),
                    benchmarkingCase.getClassName() + ".txt",
                    benchmarkingCase.getProfiler()
            );
        }
    }
}
