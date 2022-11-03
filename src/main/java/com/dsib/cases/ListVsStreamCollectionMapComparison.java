package com.dsib.cases;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Compare time consumed by plain Java foreach code versus Stream code
 */
public class ListVsStreamCollectionMapComparison {

    @State(Scope.Benchmark)
    public static class ListHolder {

        public List<Integer> intList;

        @Setup(Level.Trial)
        public void doSetup() {
            Random random = new Random();
            intList = new ArrayList<>();
            for (int i = 0; i < 300; i++) {
                intList.add(random.nextInt(0, 500));
            }
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void doStream(ListHolder holder, Blackhole blackhole) {
        List<String> strs = holder.intList.stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        blackhole.consume(strs);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void doLinkedList(ListHolder holder, Blackhole blackhole) {
        List<String> strs = new LinkedList<>();
        for (Integer i : holder.intList) {
            strs.add(i.toString());
        }
        blackhole.consume(strs);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void doArrayList(ListHolder holder, Blackhole blackhole) {
        List<String> strs = new ArrayList<>();
        for (Integer i : holder.intList) {
            strs.add(i.toString());
        }
        blackhole.consume(strs);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ListVsStreamCollectionMapComparison.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(2)
                .addProfiler(GCProfiler.class)
                .build();

        new Runner(opt).run();
    }
}
