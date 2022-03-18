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
* formula (calculating using formula)

## Build instructions

### Run simple test:

run main in FibonacciApp in FibonacciGenerator.kt

### Run as cli app:

1. mvn clean package
2. java -jar target/fibonacci-app.jar

### Run JMH benchmark:

1. mvn clean package -P benchmark
2. java -jar target/fibonacci-benchmark.jar

### Implementation details

For generators, operating with long (returning long) maximum number is 92nd = 7540113804746346429

### Benchmark results

JMH version: 1.33 VM version: JDK 11.0.7, OpenJDK 64-Bit Server VM, 11.0.7+10-LTS CPU: Intel(R) Core(TM) i7-8700B CPU @ 3.20GHz

#### On small numbers 10, 20, 30:

| Number | Algorithm          | Mode | Cnt | Score      | Error | Units |
|--------|--------------------|------|-----|------------|-------|-------|
| 10     | FORMULA            | avgt | 20  | 77.431 ±   | 0.133 | us/op |
| 10     | RECURSIVE          | avgt | 20  | 0.118 ±    | 0.002 | us/op |
| 10     | MATRIX             | avgt | 20  | 0.133 ±    | 0.001 | us/op |
| 10     | DYNAMIC            | avgt | 20  | 0.065 ±    | 0.004 | us/op |
| 10     | BOTTOM_UP          | avgt | 20  | 0.056 ±    | 0.001 | us/op |
| 10     | ITERATIVE_01       | avgt | 20  | 0.056 ±    | 0.001 | us/op |
| 10     | ITERATIVE_02       | avgt | 20  | 0.048 ±    | 0.001 | us/op |
| 10     | ITERATIVE_03       | avgt | 20  | 0.049 ±    | 0.001 | us/op |
| 10     | TAIL_RECURSIVE     | avgt | 20  | 0.007 ±    | 0.001 | us/op |
| 10     | TAIL_RECURSIVE_OPT | avgt | 20  | 0.006 ±    | 0.001 | us/op |
| 20     | FORMULA            | avgt | 20  | 140.362 ±  | 0.127 | us/op |
| 20     | RECURSIVE          | avgt | 20  | 14.548 ±   | 0.146 | us/op |
| 20     | MATRIX             | avgt | 20  | 0.200 ±    | 0.003 | us/op |
| 20     | DYNAMIC            | avgt | 20  | 0.134 ±    | 0.003 | us/op |
| 20     | ITERATIVE_01       | avgt | 20  | 0.122 ±    | 0.022 | us/op |
| 20     | BOTTOM_UP          | avgt | 20  | 0.098 ±    | 0.001 | us/op |
| 20     | ITERATIVE_02       | avgt | 20  | 0.098 ±    | 0.001 | us/op |
| 20     | ITERATIVE_03       | avgt | 20  | 0.074 ±    | 0.001 | us/op |
| 20     | TAIL_RECURSIVE     | avgt | 20  | 0.013 ±    | 0.001 | us/op |
| 20     | TAIL_RECURSIVE_OPT | avgt | 20  | 0.006 ±    | 0.001 | us/op |
| 30     | RECURSIVE          | avgt | 20  | 1796.136 ± | 2.058 | us/op |
| 30     | FORMULA            | avgt | 20  | 204.404 ±  | 0.124 | us/op |
| 30     | DYNAMIC            | avgt | 20  | 0.221 ±    | 0.004 | us/op |
| 30     | MATRIX             | avgt | 20  | 0.219 ±    | 0.001 | us/op |
| 30     | ITERATIVE_01       | avgt | 20  | 0.178 ±    | 0.030 | us/op |
| 30     | BOTTOM_UP          | avgt | 20  | 0.138 ±    | 0.001 | us/op |
| 30     | ITERATIVE_02       | avgt | 20  | 0.125 ±    | 0.001 | us/op |
| 30     | ITERATIVE_03       | avgt | 20  | 0.106 ±    | 0.001 | us/op |
| 30     | TAIL_RECURSIVE     | avgt | 20  | 0.025 ±    | 0.001 | us/op |
| 30     | TAIL_RECURSIVE_OPT | avgt | 20  | 0.007 ±    | 0.001 | us/op |

#### On large numbers (92)

| Number | Algorithm          | Mode | Cnt | Score   | Error | Units |
|--------|--------------------|------|-----|---------|-------|-------|
| 92     | DYNAMIC            | avgt | 20  | 0.699 ± | 0.003 | us/op |
| 92     | ITERATIVE_01       | avgt | 20  | 0.611 ± | 0.001 | us/op |
| 92     | BOTTOM_UP          | avgt | 20  | 0.414 ± | 0.001 | us/op |
| 92     | ITERATIVE_03       | avgt | 20  | 0.404 ± | 0.028 | us/op |
| 92     | ITERATIVE_02       | avgt | 20  | 0.384 ± | 0.056 | us/op |
| 92     | MATRIX             | avgt | 20  | 0.332 ± | 0.001 | us/op |
| 92     | TAIL_RECURSIVE     | avgt | 20  | 0.078 ± | 0.001 | us/op |
| 92     | TAIL_RECURSIVE_OPT | avgt | 20  | 0.021 ± | 0.001 | us/op |

#### On extra large numbers (1000000) [Using BigInteger]

| Number  | Algorithm          | Mode | Cnt | Score          | Error    | Units |
|---------|--------------------|------|-----|----------------|----------|-------|
| 1000000 | TAIL_RECURSIVE_OPT | avgt | 20  | 10130674.571 ± | 5235.281 | us/op |
| 1000000 | ITERATIVE_02       | avgt | 20  | 10394043.186 ± | 8216.862 | us/op |
| 1000000 | MATRIX             | avgt | 20  | 61581.599 ±    | 554.081  | us/op |

