package com.dsib.cases;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

/**
 * Compare time consumed by using return value vs throw an error
 */
public class ReturnVsThrowException {

    @State(Scope.Benchmark)
    public static class IntegerHolder {
        public String returnValue = "value";
        public String exceptionText = "exceptionText";
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public String doReturnValue() {
        return "value";
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public String doReturnValueFromHolder(IntegerHolder holder) {
        return holder.returnValue;
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void doThrown(IntegerHolder holder, Blackhole blackhole) {
        try {
            throw new IllegalArgumentException(holder.exceptionText);
        } catch (IllegalArgumentException e) {
            blackhole.consume(e);
        }
    }
}
