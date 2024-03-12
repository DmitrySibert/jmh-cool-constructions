package com.dsib.cases;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Compare time consumed by using method reference vs lambda
 */
public class MethodReferenceVsLambda {

    private static final String ADDITIONAL_STR = "add";

    @State(Scope.Benchmark)
    public static class Holder {
        public String value = "value";
    }

    public String modifyString(String str) {
        return ADDITIONAL_STR + str;
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void doMethodReference(Holder holder, Blackhole blackhole) {
        Optional.of(holder.value)
            .map(this::modifyString)
            .ifPresent(blackhole::consume);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void doLambda(Holder holder, Blackhole blackhole) {
        Optional.of(holder.value)
            .map(str -> ADDITIONAL_STR + str)
            .ifPresent(blackhole::consume);
    }
}
