package com.dsib.cases;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Compare time consumed by using of String::format vs string concat vs StringBuilder
 */
public class StringFormatVsConcatination {

    @State(Scope.Benchmark)
    public static class IntegerHolder {
        public String format = "This is %s to output %s";
        public String format1 = "This is ";
        public String format2 = " to output ";
        public String arg1 = "arg1";
        public String arg2 = "arg2";
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @BenchmarkMode(Mode.Throughput)
    public void doFormat(IntegerHolder holder, Blackhole blackhole) {
        blackhole.consume(String.format(holder.format, holder.arg1, holder.arg2));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @BenchmarkMode(Mode.Throughput)
    public void doConcat(IntegerHolder holder, Blackhole blackhole) {
        blackhole.consume(holder.format1 + holder.arg1 + holder.format2 + holder.arg2);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @BenchmarkMode(Mode.Throughput)
    public void doStringBuilder(IntegerHolder holder, Blackhole blackhole) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(holder.format1)
                .append(holder.arg1)
                .append(holder.format2)
                .append(holder.arg2);
        blackhole.consume(stringBuilder.toString());
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(StringFormatVsConcatination.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(2)
                .addProfiler(GCProfiler.class)
                .build();

        new Runner(opt).run();
    }
}
