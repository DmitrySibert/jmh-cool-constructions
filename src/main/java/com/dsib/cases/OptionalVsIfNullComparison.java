package com.dsib.cases;

import org.openjdk.jmh.annotations.*;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Compare time consumed by plain Java if-else code versus Optional::ifPresent
 */
public class OptionalVsIfNullComparison {

    @State(Scope.Benchmark)
    public static class Holder {
        public Integer value = 3;
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void DoOptional(Holder holder) {
        Optional.ofNullable(holder.value)
                .ifPresent(this::increment);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void DoIf(Holder holder) {
        if (holder.value != null) {
            increment(holder.value);
        }
    }

    public void increment(Integer value) {
        ++value;
    }
}
