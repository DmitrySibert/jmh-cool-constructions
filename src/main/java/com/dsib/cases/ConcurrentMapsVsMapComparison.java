package com.dsib.cases;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;
import java.util.concurrent.*;

/**
 * Compare time consumed by regular and multithreading collections in Write mode
 */
public class ConcurrentMapsVsMapComparison {

    private static final int CORES_NUM = Runtime.getRuntime().availableProcessors();

    @State(Scope.Benchmark)
    public static class ListHolder {

        public List<Integer> intList;
        public ExecutorService executorService;

        @Param({"20000"})
        public int size;

        @Setup(Level.Trial)
        public void doSetup() {
            intList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                intList.add(i);
            }
            Collections.shuffle(intList);
            executorService = Executors.newFixedThreadPool(CORES_NUM);
        }

        @TearDown(Level.Trial)
        public void doStop() {
            executorService.shutdown();
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void doMap(ListHolder holder, Blackhole blackhole) {
        Map<Integer, String> map = new HashMap<>();
        holder.intList.forEach(num -> map.put(num, ""));
        blackhole.consume(map);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void doConcurrentMap(ListHolder holder, Blackhole blackhole) {
        Map<Integer, String> map = new ConcurrentHashMap<>();
        holder.intList.forEach(num -> map.put(num, ""));
        blackhole.consume(map);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void doSynchronizedMap(ListHolder holder, Blackhole blackhole) {
        Map<Integer, String> map = Collections.synchronizedMap(new HashMap<>());
        holder.intList.forEach(num -> map.put(num, ""));
        blackhole.consume(map);
    }



    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void doConcurrentMapParallel(ListHolder holder, Blackhole blackhole) {
        Map<Integer, String> map = new ConcurrentHashMap<>();
        holder.intList
                .parallelStream()
                .forEach(num -> map.put(num, ""));
        blackhole.consume(map);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void doSynchronizedMapParallel(ListHolder holder, Blackhole blackhole) {
        Map<Integer, String> map = Collections.synchronizedMap(new HashMap<>());
        holder.intList
                .parallelStream()
                .forEach(num -> map.put(num, ""));
        blackhole.consume(map);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void doThreadsConcurrent(ListHolder holder, Blackhole blackhole) throws ExecutionException, InterruptedException {
        Map<Integer, String> map = new ConcurrentHashMap<>();
        List<CompletableFuture<Void>> futures = new ArrayList<>(CORES_NUM);
        int sizeOfBatches = holder.intList.size() / CORES_NUM;

        for (int i = 0; i < CORES_NUM; i++) {
            int left = i * sizeOfBatches;
            int right = left + sizeOfBatches;
            if (i == CORES_NUM - 1) {
                right += holder.intList.size() % CORES_NUM;
            }
            List<Integer> batch = holder.intList.subList(left, right);
            futures.add(CompletableFuture.runAsync(
                () -> batch.forEach(num -> map.put(num, "")),
                holder.executorService
            ));
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[CORES_NUM])).get();
        blackhole.consume(map);
        assert map.size() == holder.intList.size();
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void doThreadSynchronized(ListHolder holder, Blackhole blackhole) throws ExecutionException, InterruptedException {
        Map<Integer, String> map = Collections.synchronizedMap(new HashMap<>());
        List<CompletableFuture<Void>> futures = new ArrayList<>(CORES_NUM);
        int sizeOfBatches = holder.intList.size() / CORES_NUM;

        for (int i = 0; i < CORES_NUM; i++) {
            int left = i * sizeOfBatches;
            int right = left + sizeOfBatches;
            if (i == CORES_NUM - 1) {
                right += holder.intList.size() % CORES_NUM;
            }
            List<Integer> batch = holder.intList.subList(left, right);
            futures.add(CompletableFuture.runAsync(
                    () -> batch.forEach(num -> map.put(num, "")),
                    holder.executorService
            ));
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[CORES_NUM])).get();
        blackhole.consume(map);
        assert map.size() == holder.intList.size();
    }
}
