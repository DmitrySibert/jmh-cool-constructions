The list of benchmarks:

* Bubble vs Quick: execute
* List vs Stream: iterate, map then collect
* Optional vs If-Else: null comparison
* String Format vs String Concatenation: construct string
* Chain of functions vs Reactive chain of functions: speed\memory comparison

---
## Bubble vs Quick
```
Benchmark                                              Mode  Cnt     Score    Error   Units
BubbleVsQuick.doBubble                                thrpt   10     1.028 ±  0.091  ops/ms
BubbleVsQuick.doBubble:·gc.alloc.rate                 thrpt   10     3.767 ±  0.328  MB/sec
BubbleVsQuick.doBubble:·gc.alloc.rate.norm            thrpt   10  4096.628 ±  1.591    B/op
BubbleVsQuick.doBubble:·gc.churn.Eden_Space           thrpt   10     3.900 ±  0.327  MB/sec
BubbleVsQuick.doBubble:·gc.churn.Eden_Space.norm      thrpt   10  4240.728 ± 42.297    B/op
BubbleVsQuick.doBubble:·gc.churn.Survivor_Space       thrpt   10    ≈ 10⁻³           MB/sec
BubbleVsQuick.doBubble:·gc.churn.Survivor_Space.norm  thrpt   10     0.400 ±  0.376    B/op
BubbleVsQuick.doBubble:·gc.count                      thrpt   10    95.000           counts
BubbleVsQuick.doBubble:·gc.time                       thrpt   10    29.000               ms
BubbleVsQuick.doQuick                                 thrpt   10    19.036 ±  0.167  ops/ms
BubbleVsQuick.doQuick:·gc.alloc.rate                  thrpt   10    56.276 ±  1.059  MB/sec
BubbleVsQuick.doQuick:·gc.alloc.rate.norm             thrpt   10  4092.986 ±  0.244    B/op
BubbleVsQuick.doQuick:·gc.churn.Eden_Space            thrpt   10    57.621 ±  0.973  MB/sec
BubbleVsQuick.doQuick:·gc.churn.Eden_Space.norm       thrpt   10  4190.947 ± 27.110    B/op
BubbleVsQuick.doQuick:·gc.churn.Survivor_Space        thrpt   10     0.001 ±  0.001  MB/sec
BubbleVsQuick.doQuick:·gc.churn.Survivor_Space.norm   thrpt   10     0.048 ±  0.022    B/op
BubbleVsQuick.doQuick:·gc.count                       thrpt   10  1404.000           counts
BubbleVsQuick.doQuick:·gc.time                        thrpt   10   251.000               ms
```
---
## List vs Stream
```
Benchmark                                                                       Mode  Cnt      Score      Error   Units
ListVsStreamCollectionMapComparison.doArrayList                                 avgt   10   6520.433 ±   92.375   ns/op
ListVsStreamCollectionMapComparison.doArrayList:·gc.alloc.rate                  avgt   10   2650.509 ±   37.253  MB/sec
ListVsStreamCollectionMapComparison.doArrayList:·gc.alloc.rate.norm             avgt   10  19037.628 ±    0.010    B/op
ListVsStreamCollectionMapComparison.doArrayList:·gc.churn.Eden_Space            avgt   10   2685.012 ±   37.904  MB/sec
ListVsStreamCollectionMapComparison.doArrayList:·gc.churn.Eden_Space.norm       avgt   10  19285.438 ±    2.007    B/op
ListVsStreamCollectionMapComparison.doArrayList:·gc.churn.Survivor_Space        avgt   10      2.356 ±    0.033  MB/sec
ListVsStreamCollectionMapComparison.doArrayList:·gc.churn.Survivor_Space.norm   avgt   10     16.921 ±    0.008    B/op
ListVsStreamCollectionMapComparison.doArrayList:·gc.count                       avgt   10  65414.000             counts
ListVsStreamCollectionMapComparison.doArrayList:·gc.time                        avgt   10   9311.000                 ms
ListVsStreamCollectionMapComparison.doLinkedList                                avgt   10   6430.750 ±  107.801   ns/op
ListVsStreamCollectionMapComparison.doLinkedList:·gc.alloc.rate                 avgt   10   3069.282 ±   51.280  MB/sec
ListVsStreamCollectionMapComparison.doLinkedList:·gc.alloc.rate.norm            avgt   10  21738.815 ±    0.013    B/op
ListVsStreamCollectionMapComparison.doLinkedList:·gc.churn.Eden_Space           avgt   10   3105.858 ±   51.995  MB/sec
ListVsStreamCollectionMapComparison.doLinkedList:·gc.churn.Eden_Space.norm      avgt   10  21997.868 ±    2.731    B/op
ListVsStreamCollectionMapComparison.doLinkedList:·gc.churn.Survivor_Space       avgt   10      3.646 ±    0.061  MB/sec
ListVsStreamCollectionMapComparison.doLinkedList:·gc.churn.Survivor_Space.norm  avgt   10     25.824 ±    0.006    B/op
ListVsStreamCollectionMapComparison.doLinkedList:·gc.count                      avgt   10  75658.000             counts
ListVsStreamCollectionMapComparison.doLinkedList:·gc.time                       avgt   10  11061.000                 ms
ListVsStreamCollectionMapComparison.doStream                                    avgt   10   7412.654 ± 1713.703   ns/op
ListVsStreamCollectionMapComparison.doStream:·gc.alloc.rate                     avgt   10   2414.912 ±  558.436  MB/sec
ListVsStreamCollectionMapComparison.doStream:·gc.alloc.rate.norm                avgt   10  19302.937 ±    0.011    B/op
ListVsStreamCollectionMapComparison.doStream:·gc.churn.Eden_Space               avgt   10   2446.983 ±  565.825  MB/sec
ListVsStreamCollectionMapComparison.doStream:·gc.churn.Eden_Space.norm          avgt   10  19559.315 ±    2.336    B/op
ListVsStreamCollectionMapComparison.doStream:·gc.churn.Survivor_Space           avgt   10      1.346 ±    0.316  MB/sec
ListVsStreamCollectionMapComparison.doStream:·gc.churn.Survivor_Space.norm      avgt   10     10.762 ±    0.466    B/op
ListVsStreamCollectionMapComparison.doStream:·gc.count                          avgt   10  59607.000             counts
ListVsStreamCollectionMapComparison.doStream:·gc.time                           avgt   10   8761.000                 ms
```

---
## Optional vs If-Else
```
Benchmark                                                   Mode  Cnt           Score        Error   Units
OptionalVsIfNullComparison.DoIf                            thrpt   10  1580149419.289 ± 833691.731   ops/s
OptionalVsIfNullComparison.DoIf:·gc.alloc.rate             thrpt   10          ≈ 10⁻⁴               MB/sec
OptionalVsIfNullComparison.DoIf:·gc.alloc.rate.norm        thrpt   10          ≈ 10⁻⁸                 B/op
OptionalVsIfNullComparison.DoIf:·gc.count                  thrpt   10             ≈ 0               counts
OptionalVsIfNullComparison.DoOptional                      thrpt   10  1579859519.666 ± 747859.564   ops/s
OptionalVsIfNullComparison.DoOptional:·gc.alloc.rate       thrpt   10           0.017 ±      0.013  MB/sec
OptionalVsIfNullComparison.DoOptional:·gc.alloc.rate.norm  thrpt   10          ≈ 10⁻⁵                 B/op
OptionalVsIfNullComparison.DoOptional:·gc.count            thrpt   10             ≈ 0               counts
```

---
## String Format vs String Concatenation
```
Benchmark                                                                   Mode  Cnt         Score         Error   Units
StringFormatVsConcatination.doConcat                                       thrpt   10  22029672.851 ± 3488713.192   ops/s
StringFormatVsConcatination.doConcat:·gc.alloc.rate                        thrpt   10      1447.242 ±     228.642  MB/sec
StringFormatVsConcatination.doConcat:·gc.alloc.rate.norm                   thrpt   10        72.355 ±       0.001    B/op
StringFormatVsConcatination.doConcat:·gc.churn.Eden_Space                  thrpt   10      1464.528 ±     231.361  MB/sec
StringFormatVsConcatination.doConcat:·gc.churn.Eden_Space.norm             thrpt   10        73.219 ±       0.018    B/op
StringFormatVsConcatination.doConcat:·gc.churn.Survivor_Space              thrpt   10         0.001 ±       0.001  MB/sec
StringFormatVsConcatination.doConcat:·gc.churn.Survivor_Space.norm         thrpt   10        ≈ 10⁻⁴                  B/op
StringFormatVsConcatination.doConcat:·gc.count                             thrpt   10     35672.000                counts
StringFormatVsConcatination.doConcat:·gc.time                              thrpt   10      5147.000                    ms
StringFormatVsConcatination.doFormat                                       thrpt   10   4020312.033 ±   55208.586   ops/s
StringFormatVsConcatination.doFormat:·gc.alloc.rate                        thrpt   10      1613.977 ±      22.552  MB/sec
StringFormatVsConcatination.doFormat:·gc.alloc.rate.norm                   thrpt   10       442.171 ±       0.001    B/op
StringFormatVsConcatination.doFormat:·gc.churn.Eden_Space                  thrpt   10      1633.160 ±      22.647  MB/sec
StringFormatVsConcatination.doFormat:·gc.churn.Eden_Space.norm             thrpt   10       447.426 ±       0.104    B/op
StringFormatVsConcatination.doFormat:·gc.churn.Survivor_Space              thrpt   10         0.025 ±       0.001  MB/sec
StringFormatVsConcatination.doFormat:·gc.churn.Survivor_Space.norm         thrpt   10         0.007 ±       0.001    B/op
StringFormatVsConcatination.doFormat:·gc.count                             thrpt   10     39781.000                counts
StringFormatVsConcatination.doFormat:·gc.time                              thrpt   10      5681.000                    ms
StringFormatVsConcatination.doStringBuilder                                thrpt   10  14851443.612 ±  641334.021   ops/s
StringFormatVsConcatination.doStringBuilder:·gc.alloc.rate                 thrpt   10      2168.313 ±      93.589  MB/sec
StringFormatVsConcatination.doStringBuilder:·gc.alloc.rate.norm            thrpt   10       160.790 ±       0.001    B/op
StringFormatVsConcatination.doStringBuilder:·gc.churn.Eden_Space           thrpt   10      2194.259 ±      94.593  MB/sec
StringFormatVsConcatination.doStringBuilder:·gc.churn.Eden_Space.norm      thrpt   10       162.714 ±       0.025    B/op
StringFormatVsConcatination.doStringBuilder:·gc.churn.Survivor_Space       thrpt   10         0.012 ±       0.001  MB/sec
StringFormatVsConcatination.doStringBuilder:·gc.churn.Survivor_Space.norm  thrpt   10         0.001 ±       0.001    B/op
StringFormatVsConcatination.doStringBuilder:·gc.count                      thrpt   10     53441.000                counts
StringFormatVsConcatination.doStringBuilder:·gc.time                       thrpt   10      7122.000                    ms
```

---
## Chain of functions vs Reactive chain
```
Benchmark                                                                  Mode  Cnt      Score     Error   Units
CodeVsRxCollectionMapComparison.doArrayList                                avgt   10   3229.821 ±  92.606   ns/op
CodeVsRxCollectionMapComparison.doArrayList:·gc.alloc.rate                 avgt   10   2721.657 ±  77.832  MB/sec
CodeVsRxCollectionMapComparison.doArrayList:·gc.alloc.rate.norm            avgt   10   9679.566 ±   0.004    B/op
CodeVsRxCollectionMapComparison.doArrayList:·gc.churn.Eden_Space           avgt   10   2754.130 ±  78.670  MB/sec
CodeVsRxCollectionMapComparison.doArrayList:·gc.churn.Eden_Space.norm      avgt   10   9795.063 ±   0.724    B/op
CodeVsRxCollectionMapComparison.doArrayList:·gc.churn.Survivor_Space       avgt   10      0.985 ±   0.028  MB/sec
CodeVsRxCollectionMapComparison.doArrayList:·gc.churn.Survivor_Space.norm  avgt   10      3.505 ±   0.002    B/op
CodeVsRxCollectionMapComparison.doArrayList:·gc.count                      avgt   10  67089.000            counts
CodeVsRxCollectionMapComparison.doArrayList:·gc.time                       avgt   10  10816.000                ms
CodeVsRxCollectionMapComparison.doRx                                       avgt   10   5356.152 ± 181.842   ns/op
CodeVsRxCollectionMapComparison.doRx:·gc.alloc.rate                        avgt   10   1761.715 ±  63.979  MB/sec
CodeVsRxCollectionMapComparison.doRx:·gc.alloc.rate.norm                   avgt   10  10387.035 ±  25.481    B/op
CodeVsRxCollectionMapComparison.doRx:·gc.churn.Eden_Space                  avgt   10   1782.981 ±  64.542  MB/sec
CodeVsRxCollectionMapComparison.doRx:·gc.churn.Eden_Space.norm             avgt   10  10512.436 ±  24.592    B/op
CodeVsRxCollectionMapComparison.doRx:·gc.churn.Survivor_Space              avgt   10      0.452 ±   0.136  MB/sec
CodeVsRxCollectionMapComparison.doRx:·gc.churn.Survivor_Space.norm         avgt   10      2.659 ±   0.715    B/op
CodeVsRxCollectionMapComparison.doRx:·gc.count                             avgt   10  43425.000            counts
CodeVsRxCollectionMapComparison.doRx:·gc.time                              avgt   10   7299.000                ms
```

---
## Return value vs throw exception
```
Benchmark                                                Mode  Cnt  Score   Error  Units
```


---
## Method reference vs Lambda
```
Benchmark                                                Mode  Cnt  Score   Error  Units
```


---
## Regular and multithreading collections in Write mode
```
Benchmark                                                Mode  Cnt  Score   Error  Units
ConcurrentMapsVsMapComparison.doMap                      avgt    6  0,383 ± 0,028  ms/op
ConcurrentMapsVsMapComparison.doConcurrentMap            avgt    6  1,301 ± 0,387  ms/op
ConcurrentMapsVsMapComparison.doSynchronizedMap          avgt    6  0,595 ± 0,024  ms/op
ConcurrentMapsVsMapComparison.doConcurrentMapParallel    avgt    6  0,824 ± 0,121  ms/op
ConcurrentMapsVsMapComparison.doSynchronizedMapParallel  avgt    6  2,731 ± 0,051  ms/op
ConcurrentMapsVsMapComparison.doThreadsConcurrent        avgt    6  0,319 ± 0,048  ms/op
ConcurrentMapsVsMapComparison.doThreadSynchronized       avgt    6  2,771 ± 0,066  ms/op





Benchmark                               (size)  Mode  Cnt   Score   Error  Units
MonitorLockCostErrorDemo.doSortAvg       20000  avgt    2  60,393          ns/op
MonitorLockCostErrorDemo.doSortLockAvg   20000  avgt    2  72,166          ns/op

MonitorLockCostErrorDemo.doSortAvg      200000  avgt    6  238,013 ± 30,911  ns/op
MonitorLockCostErrorDemo.doSortLockAvg  200000  avgt    6  263,990 ± 13,608  ns/op