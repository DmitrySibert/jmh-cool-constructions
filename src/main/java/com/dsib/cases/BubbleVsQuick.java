package com.dsib.cases;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Compare time consumed by Bubble sort vs Quick sort
 */
public class BubbleVsQuick {

    @State(Scope.Benchmark)
    public static class ArrayHolder {

        public int[] intList;

        @Setup(Level.Invocation)
        public void doSetup() {
            Random random = new Random();
            intList = new int[10000];
            for (int i = 0; i < intList.length; i++) {
                intList[i] = random.nextInt(0, 500);
            }
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void doQuick(ArrayHolder holder, Blackhole blackhole) {
        quickSort(holder.intList, 0, holder.intList.length - 1);
        blackhole.consume(holder.intList);
    }

    public void quickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        int swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void doBubble(ArrayHolder holder, Blackhole blackhole) {
        int i, j, temp;
        for (i = 0; i < holder.intList.length - 1; i++) {
            for (j = 0; j < holder.intList.length - i - 1; j++) {
                if (holder.intList[j] > holder.intList[j + 1]) {
                    temp = holder.intList[j];
                    holder.intList[j] = holder.intList[j + 1];
                    holder.intList[j + 1] = temp;
                }
            }
        }
        blackhole.consume(holder.intList);
    }
}
