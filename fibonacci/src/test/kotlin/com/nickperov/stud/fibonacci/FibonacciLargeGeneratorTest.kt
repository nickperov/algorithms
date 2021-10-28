package com.nickperov.stud.fibonacci

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigInteger

class FibonacciLargeGeneratorTest {

    // number 93,94,95
    private val numbers = arrayOf(
        BigInteger("12200160415121876738"), BigInteger("19740274219868223167"), BigInteger("31940434634990099905")
    )

    private val generators = arrayOf(
        FibonacciLargeTailRecursiveOptimisedGenerator(),
        FibonacciLargeIterativeGenerator02(),
        FibonacciLargeMatrixGenerator()
    )

    @Test
    fun basicTest() {
        for (generator in generators) {
            for (index in numbers.indices) {
                assertEquals(numbers[index], generator.calculate(index + 93))
            }
        }
    }

    @Test
    fun compareSmallNumbersTest() {
        val smallNumberGenerator = FibonacciTailRecursiveOptimisedGenerator()
        for (i in 0..92) {
            val number = smallNumberGenerator.calculate(i)
            for (generatorIndex in 1 until generators.size) {
                assertEquals(number, generators[generatorIndex].calculate(i).toLong())
            }
        }
    }

    @Test
    fun advancedCompareTest() {
        for (i in 0..10000) {
            assertEquals(1, generators.takeLast(generators.size - 1).map { it.calculate(i) }.distinct().count())
        }
    }
}