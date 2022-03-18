package com.nickperov.stud.algorithms.fibonacci

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

object FibonacciApp {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Hello, Fibonacci numbers")

        for (number in 0..10) {
            println("====================>$number<==================")
            println(FibonacciRecursiveGenerator().calculate(number))
            println(FibonacciTailRecursiveGenerator().calculate(number))
            println(FibonacciTailRecursiveOptimisedGenerator().calculate(number))
            println(FibonacciDynamicGenerator().calculate(number))
            println(FibonacciBottomUpGenerator().calculate(number))
            println(FibonacciIterativeGenerator01().calculate(number))
            println(FibonacciIterativeGenerator02().calculate(number))
            println(FibonacciIterativeGenerator03().calculate(number))
            println(FibonacciMatrixGenerator().calculate(number))
        }
    }
}


abstract class FibonacciGenerator {
    abstract fun calculate(number: Int): Long
}

class FibonacciRecursiveGenerator : FibonacciGenerator() {

    override fun calculate(number: Int): Long {
        return if (number == 0) {
            0L
        } else if (number == 1 || number == 2)
            1L
        else calculate(number - 1) + calculate(number - 2)
    }
}

class FibonacciTailRecursiveGenerator : FibonacciGenerator() {

    override fun calculate(number: Int): Long {
        return if (number == 0) {
            0L
        } else if (number == 1 || number == 2) {
            1L
        } else calculate(1, 1, 3, number)
    }

    private fun calculate(antePenultimate: Long, penultimate: Long, current: Int, target: Int): Long {
        return if (current == target) antePenultimate + penultimate
        else calculate(penultimate, antePenultimate + penultimate, current + 1, target)
    }
}

class FibonacciTailRecursiveOptimisedGenerator : FibonacciGenerator() {

    override fun calculate(number: Int): Long {
        return if (number == 0)
            0L
        else if (number == 1 || number == 2)
            1L
        else calculate(1, 1, 3, number)
    }

    private tailrec fun calculate(antePenultimate: Long, penultimate: Long, current: Int, target: Int): Long {
        return if (current == target) antePenultimate + penultimate
        else calculate(penultimate, antePenultimate + penultimate, current + 1, target)
    }
}

class FibonacciDynamicGenerator : FibonacciGenerator() {

    override fun calculate(number: Int): Long {
        val accumulator = if (number > 2) {
            Array(number - 2) { -1L }
        } else {
            Array(0) { -1L }
        }
        return calculate(number, accumulator)
    }

    private fun calculate(number: Int, accumulator: Array<Long>): Long {
        if (number == 0) {
            return 0L
        } else if (number == 1 || number == 2) {
            return 1L
        }

        val index = number - 3
        val storedValue = accumulator[index]
        return if (storedValue != -1L) {
            storedValue
        } else {
            val value = calculate(number - 1, accumulator) + calculate(number - 2, accumulator)
            accumulator[index] = value
            value
        }
    }
}

class FibonacciBottomUpGenerator : FibonacciGenerator() {
    override fun calculate(number: Int): Long {
        val accumulator = Array(number) { -1L }
        return calculate(number, accumulator)
    }

    private fun calculate(number: Int, accumulator: Array<Long>): Long {

        if (number == 0) {
            return 0L
        }

        for (i in 0 until number) {
            if (i <= 1) {
                accumulator[i] = 1L
            } else {
                accumulator[i] = accumulator[i - 1] + accumulator[i - 2]
            }
        }

        return accumulator[number - 1]
    }
}

class FibonacciIterativeGenerator01 : FibonacciGenerator() {
    override fun calculate(number: Int): Long {

        var numberPair = Pair(-1L, -1L)
        for (i in 0..number) {
            numberPair = calculate(i, numberPair)
        }

        return numberPair.second
    }

    private fun calculate(number: Int, prev: Pair<Long, Long>): Pair<Long, Long> {
        return when (number) {
            0 -> Pair(-1L, 0L)
            1 -> Pair(-1L, 1L)
            2 -> Pair(1L, 1L)
            else -> Pair(prev.second, prev.first + prev.second)
        }
    }
}

class FibonacciIterativeGenerator02 : FibonacciGenerator() {
    override fun calculate(number: Int): Long {

        val numberPair = arrayOf(-1L, -1L)
        for (i in 0..number) {
            calculate(i, numberPair)
        }

        return numberPair[1]
    }

    private fun calculate(number: Int, prev: Array<Long>) {
        when (number) {
            0 -> {
                prev[1] = 0L
            }
            1 -> {
                prev[1] = 1L
            }
            2 -> {
                prev[0] = 1L
            }
            else -> {
                val next = prev[0] + prev[1]
                prev[0] = prev[1]
                prev[1] = next
            }

        }
    }
}

class FibonacciIterativeGenerator03 : FibonacciGenerator() {
    override fun calculate(number: Int): Long {

        val numberMem = arrayOf(-1L, -1L)
        var numberValue = 0L;
        for (i in 1..number) {
            numberValue = calculate(i, numberMem)
        }

        return numberValue
    }

    private fun calculate(number: Int, prev: Array<Long>): Long {
        return when (number) {
            1 -> {
                prev[0] = 1L
                1L
            }
            2 -> {
                prev[1] = 1L
                1L
            }
            else -> {
                val next = prev[0] + prev[1]
                val nextIndex = number.rem(2)
                prev[nextIndex] = next
                next
            }
        }
    }
}


class FibonacciMatrixGenerator : FibonacciGenerator() {
    /**
     *  Matrix:
    | 0 1 |
    | 1 1 |
     */
    private val matrix = arrayOf(LongArray(2) { it.toLong() }, LongArray(2) { 1L })

    override fun calculate(number: Int): Long {
        if (number == 0 || number == 1) {
            return number.toLong()
        } else if (number == 2) {
            return 1L
        } else {
            var currMatrix = matrix
            val power = number - 1
            //val isEven: Boolean = power % 2 != 0

            currMatrix = powerOf(currMatrix, power)

            /*if (isEven) {
                currMatrix = multiply(currMatrix, matrix)
            }*/

            return currMatrix[1][1]
        }
    }

    private fun powerOf(m: Array<LongArray>, n: Int): Array<LongArray> {
        return when {
            n == 1 -> {
                m
            }
            n % 2 == 0 -> {
                val mP = powerOf(m, n / 2)
                multiply(mP, mP)
            }
            else -> {
                val mP = powerOf(m, n - 1)
                multiply(mP, m)
            }
        }
    }

    private fun multiply(m1: Array<LongArray>, m2: Array<LongArray>): Array<LongArray> {
        val l = m1.size
        val n = m2.size
        val result = Array(l) { LongArray(n) { 0L } }

        for (i in 0 until l) {
            for (k in 0 until n) {
                var sum = 0L
                for (j in 0 until m1[i].size) {
                    sum += m1[i][j] * m2[k][j]
                }
                result[i][k] = sum
            }
        }

        return result
    }
}

class FibonacciFormulaGenerator : FibonacciGenerator() {

    companion object {

        val ROOT_OF_FIVE = BigDecimal.valueOf(5L).sqrt(MathContext.DECIMAL128);
        val GOLDEN_RATIO = BigDecimal.ONE.add(ROOT_OF_FIVE).divide(BigDecimal.valueOf(2))
        val PSI = BigDecimal.ONE.subtract(ROOT_OF_FIVE).divide(BigDecimal.valueOf(2))
    }

    override fun calculate(number: Int): Long {
        val d = (GOLDEN_RATIO.pow(number).subtract(PSI.pow(number))).divide(ROOT_OF_FIVE)
        /*println(d)*/
        return d.setScale(0, RoundingMode.HALF_UP).toLong()
    }
}