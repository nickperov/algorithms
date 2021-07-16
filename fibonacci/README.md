# Fibonacci number calculation module

## Description

Multiple example of fibonacci numbers calculation, including:

* recursive
* tail recursive
* tail recursive (kotlin optimized)
* dynamic (memorization)
* bottom up
* iterative (multiple variants)
* matrix (logarithmic)

## Build instructions

### Run simple test:

run main in FibonacciGenerator.kt

### Run JMH benchmark:

1. mvn clean package
2. java -jar target/fibonacci-benchmark.jar

### Benchmark results

#### On small numbers:
```
Benchmark                                          (aNumber)        (bAlgorithm)  Mode  Cnt     Score    Error  Units
FibonacciGeneratorBenchmark.runFibonacciGenerator         10           RECURSIVE  avgt   20     0.124 ±  0.001  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         10      TAIL_RECURSIVE  avgt   20     0.007 ±  0.001  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         10  TAIL_RECURSIVE_OPT  avgt   20     0.006 ±  0.001  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         10             DYNAMIC  avgt   20     0.066 ±  0.002  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         10           BOTTOM_UP  avgt   20     0.057 ±  0.001  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         10        ITERATIVE_01  avgt   20     0.066 ±  0.006  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         10        ITERATIVE_02  avgt   20     0.050 ±  0.002  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         10        ITERATIVE_03  avgt   20     0.052 ±  0.001  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         10              MATRIX  avgt   20     0.141 ±  0.001  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         20           RECURSIVE  avgt   20    14.979 ±  0.118  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         20      TAIL_RECURSIVE  avgt   20     0.014 ±  0.001  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         20  TAIL_RECURSIVE_OPT  avgt   20     0.006 ±  0.001  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         20             DYNAMIC  avgt   20     0.140 ±  0.004  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         20           BOTTOM_UP  avgt   20     0.102 ±  0.001  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         20        ITERATIVE_01  avgt   20     0.139 ±  0.001  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         20        ITERATIVE_02  avgt   20     0.082 ±  0.001  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         20        ITERATIVE_03  avgt   20     0.077 ±  0.001  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         20              MATRIX  avgt   20     0.203 ±  0.001  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         30           RECURSIVE  avgt   20  1841.470 ± 10.291  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         30      TAIL_RECURSIVE  avgt   20     0.025 ±  0.001  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         30  TAIL_RECURSIVE_OPT  avgt   20     0.007 ±  0.001  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         30             DYNAMIC  avgt   20     0.234 ±  0.006  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         30           BOTTOM_UP  avgt   20     0.152 ±  0.003  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         30        ITERATIVE_01  avgt   20     0.224 ±  0.004  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         30        ITERATIVE_02  avgt   20     0.131 ±  0.015  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         30        ITERATIVE_03  avgt   20     0.123 ±  0.012  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator         30              MATRIX  avgt   20     0.228 ±  0.007  us/op
```
#### On large numbers (999)
```
Benchmark                                          (aNumber)        (bAlgorithm)  Mode Cnt Score Error Units 
FibonacciGeneratorBenchmark.runFibonacciGenerator        999      TAIL_RECURSIVE  avgt   20  0.776 ± 0.002  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator        999  TAIL_RECURSIVE_OPT  avgt   20  0.257 ± 0.001  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator        999             DYNAMIC  avgt   20  9.115 ± 0.045  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator        999           BOTTOM_UP  avgt   20  7.532 ± 0.065  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator        999        ITERATIVE_01  avgt   20  7.456 ± 0.278  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator        999        ITERATIVE_02  avgt   20  4.884 ± 1.329  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator        999        ITERATIVE_03  avgt   20  3.801 ± 0.246  us/op
FibonacciGeneratorBenchmark.runFibonacciGenerator        999              MATRIX  avgt   20  0.485 ± 0.001  us/op
```
