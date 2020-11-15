package com.nickperov.stud.fibonacci.benchmark

import com.nickperov.stud.fibonacci.*
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 2, warmups = 1)
@Warmup(iterations = 5)
@Measurement(iterations = 10)
@State(Scope.Benchmark)
open class FibonacciGeneratorBenchmark {

    enum class GeneratorType { RECURSIVE, TAIL_RECURSIVE, TAIL_RECURSIVE_OPT, DYNAMIC, BOTTOM_UP, ITERATIVE_01, ITERATIVE_02, ITERATIVE_03}

    @Param("10", "20", "30")
    var number: Int = 0
    
    @Param("RECURSIVE", "TAIL_RECURSIVE", "TAIL_RECURSIVE_OPT", "DYNAMIC", "BOTTOM_UP", "ITERATIVE_01", "ITERATIVE_02", "ITERATIVE_03")
    lateinit var generatorType: GeneratorType
    
    private lateinit var fibonacciGenerator: FibonacciGenerator
    
    @Setup
    fun initGenerator() {
        fibonacciGenerator = when(generatorType) {
            GeneratorType.RECURSIVE -> FibonacciRecursiveGenerator()
            GeneratorType.TAIL_RECURSIVE -> FibonacciTailRecursiveGenerator()
            GeneratorType.TAIL_RECURSIVE_OPT -> FibonacciTailRecursiveOptimisedGenerator()
            GeneratorType.DYNAMIC -> FibonacciDynamicGenerator()
            GeneratorType.BOTTOM_UP -> FibonacciBottomUpGenerator()
            GeneratorType.ITERATIVE_01 -> FibonacciIterativeGenerator01()
            GeneratorType.ITERATIVE_02 -> FibonacciIterativeGenerator02()
            GeneratorType.ITERATIVE_03 -> FibonacciIterativeGenerator03()
        }
    }
    
    @Benchmark
     fun runFibonacciGenerator(): Long {
        return fibonacciGenerator.calculate(number)
    }
}