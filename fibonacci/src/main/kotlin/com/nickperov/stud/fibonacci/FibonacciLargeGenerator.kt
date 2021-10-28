package com.nickperov.stud.fibonacci

import java.math.BigInteger

fun main() {
    println("Hello, large Fibonacci numbers")

    println(FibonacciLargeTailRecursiveOptimisedGenerator().calculate(1000000))

    /*for (number in 93..150) {
        println("====================>$number<==================")
        println(FibonacciLargeTailRecursiveOptimisedGenerator().calculate(number))
        println(FibonacciLargeIterativeGenerator02().calculate(number))
        println(FibonacciLargeMatrixGenerator().calculate(number))
    }*/
}

/**
 * Generator to calculate fibonacci number greater than 7540113804746346429 (92nd number)
 */
abstract class FibonacciLargeGenerator {
    abstract fun calculate(number: Int): BigInteger
}

class FibonacciLargeTailRecursiveOptimisedGenerator : FibonacciLargeGenerator() {

    override fun calculate(number: Int): BigInteger {
        return if (number == 0)
            BigInteger.ZERO
        else if (number == 1 || number == 2)
            BigInteger.ONE
        else calculate(BigInteger.ONE, BigInteger.ONE, 3, number)
    }

    private tailrec fun calculate(antePenultimate: BigInteger, penultimate: BigInteger, current: Int, target: Int): BigInteger {
        return if (current == target) antePenultimate.add(penultimate)
        else calculate(penultimate, antePenultimate.add(penultimate), current + 1, target)
    }
}

class FibonacciLargeIterativeGenerator02 : FibonacciLargeGenerator() {
    override fun calculate(number: Int): BigInteger {

        val numberPair = arrayOf(BigInteger.ZERO, BigInteger.ZERO)
        for (i in 0..number) {
            calculate(i, numberPair)
        }

        return numberPair[1]
    }

    private fun calculate(number: Int, prev: Array<BigInteger>) {
        when (number) {
            0 -> {
                prev[1] = BigInteger.ZERO
            }
            1 -> {
                prev[1] = BigInteger.ONE
            }
            2 -> {
                prev[0] = BigInteger.ONE
            }
            else -> {
                val next = prev[0].add(prev[1])
                prev[0] = prev[1]
                prev[1] = next
            }

        }
    }
}

class FibonacciLargeMatrixGenerator : FibonacciLargeGenerator() {
    /**
     *  Matrix:
    | 0 1 |
    | 1 1 |
     */
    private val matrix = arrayOf(arrayOf(BigInteger.ZERO, BigInteger.ONE), arrayOf(BigInteger.ONE, BigInteger.ONE))

    override fun calculate(number: Int): BigInteger {
        if (number == 0 || number == 1) {
            return BigInteger.valueOf(number.toLong())
        } else if (number == 2) {
            return BigInteger.ONE
        } else {
            var currMatrix = matrix
            val power = number - 1

            currMatrix = powerOf(currMatrix, power)


            return currMatrix[1][1]
        }
    }

    private fun powerOf(m: Array<Array<BigInteger>>, n: Int): Array<Array<BigInteger>> {
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

    private fun multiply(m1: Array<Array<BigInteger>>, m2: Array<Array<BigInteger>>): Array<Array<BigInteger>> {
        val l = m1.size
        val n = m2.size
        val result = Array(l) { Array(n) { BigInteger.ZERO } }

        for (i in 0 until l) {
            for (k in 0 until n) {
                var sum = BigInteger.ZERO
                for (j in 0 until m1[i].size) {
                    sum = sum.add(m1[i][j].multiply(m2[k][j]))
                }
                result[i][k] = sum
            }
        }

        return result
    }
}