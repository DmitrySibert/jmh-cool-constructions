package com.dsib.cases;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

/**
 * Compare time consumed by using return value vs throw an error
 */
public class MonitorLockCost {

    @State(Scope.Benchmark)
    public static class Holder {
        public final Object lock = new Object();
        public int k = 10;
    }


    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @BenchmarkMode(Mode.Throughput)
    public void doSort(Holder holder, Blackhole blackhole) {
        holder.k++;
        blackhole.consume(holder.k);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @BenchmarkMode(Mode.Throughput)
    public void doSortLock(Holder holder, Blackhole blackhole) {
        synchronized (holder.lock) {
            holder.k++;
            blackhole.consume(holder.k);
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
