package com.dsib.cases;

import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class AllRunner {

    private static final Integer WARMUP = 5;
    private static final Integer ITERATIONS = 5;
    private static final Integer FORKS = 2;

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder()
                .include(BubbleVsQuick.class.getSimpleName())
                .warmupIterations(WARMUP)
                .measurementIterations(ITERATIONS)
                .forks(FORKS)
                .addProfiler(GCProfiler.class)
                .build()
        ).run();

        new Runner(new OptionsBuilder()
                .include(CodeVsRxCollectionMapComparison.class.getSimpleName())
                .warmupIterations(WARMUP)
                .measurementIterations(ITERATIONS)
                .forks(FORKS)
                .addProfiler(GCProfiler.class)
                .build()
        ).run();

        new Runner(new OptionsBuilder()
                .include(ListVsStreamCollectionMapComparison.class.getSimpleName())
                .warmupIterations(WARMUP)
                .measurementIterations(ITERATIONS)
                .forks(FORKS)
                .addProfiler(GCProfiler.class)
                .build()
        ).run();

        new Runner(new OptionsBuilder()
                .include(OptionalVsIfNullComparison.class.getSimpleName())
                .warmupIterations(WARMUP)
                .measurementIterations(ITERATIONS)
                .forks(FORKS)
                .addProfiler(GCProfiler.class)
                .build()
        ).run();

        new Runner(new OptionsBuilder()
                .include(StringFormatVsConcatination.class.getSimpleName())
                .warmupIterations(WARMUP)
                .measurementIterations(ITERATIONS)
                .forks(FORKS)
                .addProfiler(GCProfiler.class)
                .build()
        ).run();
    }
}
