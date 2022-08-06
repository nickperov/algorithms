# Combinatorics module

## Description

Basic combinatorial operations

## Build instructions

### Run simple test:

### Run as cli app:

### Run JMH benchmark:

1. mvn clean package -P benchmark
2. java -jar target/combinatorics-benchmark.jar

### Implementation details

### Benchmark results

#### Permutations calculation result 

JMH version: 1.35 VM version: JDK 17.0.4, OpenJDK Runtime Environment Corretto-17.0.4.8.1 (build 17.0.4+8-LTS) CPU: Intel(R) Core(TM) i7-8700B CPU @ 3.20GHz

| Benchmark | Mode | Cnt | Score       | Error   | Units |
|-----------|------|-----|-------------|---------|-------|
| runV1     | avgt | 20  | 39246.110 ± | 966.601 | us/op |
| runV2     | avgt | 20  | 40325.041 ± | 227.542 | us/op |
| runV3     | avgt | 20  | 30686.020 ± | 680.942 | us/op |
| runV4     | avgt | 20  | 33304.858 ± | 444.740 | us/op |

runV1 - uses array invariant of pre-calculated size  
runV2 - uses list invariant  
runV3 - optimized (reduced number of array copy operations), uses array invariant of pre-calculated size  
runV4 - non-recursive implementation, optimized (reduced number of array copy operations), uses array of pre-calculated size  

