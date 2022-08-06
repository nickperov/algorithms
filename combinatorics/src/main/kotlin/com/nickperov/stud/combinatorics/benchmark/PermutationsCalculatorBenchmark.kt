package com.nickperov.stud.combinatorics.benchmark

import com.nickperov.stud.combinatorics.experiment.PermutationsCalculatorExperiment
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 2, warmups = 1)
@Warmup(iterations = 5)
@Measurement(iterations = 10)
@State(Scope.Benchmark)
open class PermutationsCalculatorBenchmark {

    private val elements = "ABCDEFGHI".toCharArray().toTypedArray()

    @Benchmark
    fun runV1() {
        PermutationsCalculatorExperiment.getAllPermutationsV1(elements)
    }

    @Benchmark
    fun runV2() {
        PermutationsCalculatorExperiment.getAllPermutationsV2(elements)
    }

    @Benchmark
    fun runV3() {
        PermutationsCalculatorExperiment.getAllPermutationsV3(elements)
    }

    @Benchmark
    fun runV4() {
        PermutationsCalculatorExperiment.getAllPermutationsV4(elements)
    }
}