package com.nickperov.stud.fibonacci

fun main() {
    println("Hello, Fibonacci numbers")

    val number = 0

    println(FibonacciRecursiveGenerator().calculate(number))
    println(FibonacciTailRecursiveGenerator().calculate(number))
    println(FibonacciTailRecursiveOptimisedGenerator().calculate(number))
    println(FibonacciDynamicGenerator().calculate(number))
    println(FibonacciBottomUpGenerator().calculate(number))
    println(FibonacciIterativeGenerator01().calculate(number))
    println(FibonacciIterativeGenerator02().calculate(number))
    println(FibonacciIterativeGenerator03().calculate(number))


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