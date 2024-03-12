package com.dsib.cases;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Compare time consumed by using return value vs throw an error
 */
public class InvocationLevelSetupIssue {

    @State(Scope.Benchmark)
    public static class Holder {
        public final Object lock = new Object();

        public List<Integer> list;
        public int k;

        @Param({"200000"})
        public int size;

        @Setup(Level.Invocation)
        public void doSetup() {
            Random random = new Random();
            list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                list.add(random.nextInt(0, 500));
            }
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void doSortAvg(Holder holder, Blackhole blackhole) {
        holder.k++;
        blackhole.consume(holder.k);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void doSortLockAvg(Holder holder, Blackhole blackhole) {
        synchronized (holder.lock) {
            holder.k++;
            blackhole.consume(holder.k);
        }
    }
}
