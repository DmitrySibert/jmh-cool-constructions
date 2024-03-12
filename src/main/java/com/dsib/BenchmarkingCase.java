package com.dsib;

import org.openjdk.jmh.profile.Profiler;

public class BenchmarkingCase {

    private final String className;
    private final Class<? extends Profiler> profiler;

    public BenchmarkingCase(String className) {
        this(className, null);
    }

    public BenchmarkingCase(String className, Class<? extends Profiler> profiler) {
        this.className = className;
        this.profiler = profiler;
    }

    public String getClassName() {
        return className;
    }

    public Class<? extends Profiler> getProfiler() {
        return profiler;
    }
}
