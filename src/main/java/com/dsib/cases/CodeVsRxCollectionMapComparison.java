package com.dsib.cases;

import io.reactivex.rxjava3.core.Flowable;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Compare time consumed by plain Java code versus RxJava operators chain.
 */
public class CodeVsRxCollectionMapComparison {

    @State(Scope.Benchmark)
    public static class ListHolder {

        public List<Integer> intList;

        @Setup(Level.Trial)
        public void doSetup() {
            Random random = new Random();
            intList = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                intList.add(random.nextInt(0, 500));
            }
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void doRx(ListHolder holder, Blackhole blackhole) {
        List<String> strs = Flowable.fromIterable(holder.intList)
                .flatMap(this::incRxWrapper)
                .map(str -> str + "add")
                .toList()
                .blockingGet();
        blackhole.consume(strs);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void doArrayList(ListHolder holder, Blackhole blackhole) {
        List<String> strs = new LinkedList<>();
        for (Integer i : holder.intList) {
            strs.add(inc(i) + "add");
        }
        blackhole.consume(strs);
    }

    private Integer inc(Integer value) {
        return value + 10;
    }

    private Flowable<Integer> incRxWrapper(Integer value) {
        return Flowable.fromCallable(() -> this.inc(value));
    }
}
