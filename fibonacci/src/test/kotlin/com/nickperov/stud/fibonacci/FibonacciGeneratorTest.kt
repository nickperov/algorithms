package com.nickperov.stud.fibonacci

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FibonacciGeneratorTest {

    val numbers = longArrayOf(
        0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L, 55L, 89L, 144L, 233L, 377L, 610L, 987L, 1597L, 2584L,
        4181L, 6765L, 10946L, 17711L, 28657L, 46368L, 75025L, 121393L, 196418L, 317811L
    )

    val generators = arrayOf(
        FibonacciRecursiveGenerator(),
        FibonacciTailRecursiveGenerator(),
        FibonacciTailRecursiveOptimisedGenerator(),
        FibonacciDynamicGenerator(),
        FibonacciBottomUpGenerator(),
        FibonacciIterativeGenerator01(),
        FibonacciIterativeGenerator02(),
        FibonacciIterativeGenerator03(),
        FibonacciMatrixGenerator()
    )

    @Test
    fun basicTest() {
        for (generator in generators) {
            for (number in numbers.indices) {
                assertEquals(numbers[number], generator.calculate(number))
            }
        }
    }

    @Test
    fun compareTest() {
        val recursiveGenerator = generators[0]
        for (i in 0..50) {
            val number = recursiveGenerator.calculate(i)
            for (generatorIndex in 1 until generators.size) {
                assertEquals(number, generators[generatorIndex].calculate(i))
            }
        }
    }

    @Test
    fun advancedCompareTest() {
        for (i in 0..1000) {
            assertEquals(1, generators.takeLast(generators.size - 1).map { it.calculate(i) }.distinct().count())
        }
    }
}