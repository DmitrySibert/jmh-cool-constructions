package com.dsib.cases;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Compare time consumed by plain Java if-else code versus Optional::ifPresent
 */
public class OptionalVsIfNullComparison {

    @State(Scope.Benchmark)
    public static class IntegerHolder {
        public Integer integer = 3;
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @BenchmarkMode(Mode.Throughput)
    public void DoOptional(IntegerHolder holder) {
        Optional.ofNullable(holder.integer)
                .ifPresent(this::inc);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @BenchmarkMode(Mode.Throughput)
    public void DoIf(IntegerHolder holder) {
        if (holder.integer != null) {
            inc(holder.integer);
        }
    }

    public Integer inc(Integer integer) {
        return ++integer;
    }
}
