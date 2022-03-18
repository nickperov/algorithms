package com.nickperov.stud.algorithms.fibonacci.benchmark

import com.nickperov.stud.algorithms.fibonacci.FibonacciLargeGenerator
import com.nickperov.stud.algorithms.fibonacci.FibonacciLargeIterativeGenerator02
import com.nickperov.stud.algorithms.fibonacci.FibonacciLargeMatrixGenerator
import com.nickperov.stud.algorithms.fibonacci.FibonacciLargeTailRecursiveOptimisedGenerator
import org.openjdk.jmh.annotations.*
import java.math.BigInteger
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 2, warmups = 1)
@Warmup(iterations = 5)
@Measurement(iterations = 10)
@State(Scope.Benchmark)
open class FibonacciLargeGeneratorBenchmark {

    enum class GeneratorType { TAIL_RECURSIVE_OPT, ITERATIVE_02, MATRIX }

    @Param("1000000")
    var aNumber: Int = 0

    @Param("TAIL_RECURSIVE_OPT", "ITERATIVE_02", "MATRIX")
    lateinit var bAlgorithm: GeneratorType

    private lateinit var fibonacciGenerator: FibonacciLargeGenerator

    @Setup
    fun initGenerator() {
        fibonacciGenerator = when (bAlgorithm) {
            GeneratorType.TAIL_RECURSIVE_OPT -> FibonacciLargeTailRecursiveOptimisedGenerator()
            GeneratorType.ITERATIVE_02 -> FibonacciLargeIterativeGenerator02()
            GeneratorType.MATRIX -> FibonacciLargeMatrixGenerator()
        }
    }

    @Benchmark
    fun runFibonacciLargeGenerator(): BigInteger {
        return fibonacciGenerator.calculate(aNumber)
    }
}