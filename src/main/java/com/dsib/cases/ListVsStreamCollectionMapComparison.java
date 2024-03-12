package com.dsib.cases;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Compare time consumed by plain Java foreach code versus Stream code
 */
public class ListVsStreamCollectionMapComparison {

    private static final String DEFAULT_COLLECTION_SIZE = "4000";

    @State(Scope.Benchmark)
    public static class ListHolder {

        public List<Integer> intList;

        @Param({DEFAULT_COLLECTION_SIZE})
        public int size;

        @Setup(Level.Trial)
        public void doSetup() {
            Random random = new Random();
            intList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
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
}
