package com.nickperov.stud.combinatorics

import java.math.BigInteger

object CombinatorialUtils {

    /**
     * Number of ordered samples of size m, without replacement, from n objects.
     */
    fun calculateNumberOfVariationsInt(n: Int, m: Int): Int {
        val number = calculateNumberOfVariations(n, m)
        if (number > BigInteger.valueOf(Int.MAX_VALUE.toLong())) {
            throw IllegalArgumentException("Value is too big")
        } else {
            return number.toInt()
        }
    }

    /**
     * Number of ordered samples of size m, without replacement, from n objects.
     */
    fun calculateNumberOfVariationsLong(n: Int, m: Int): Long {
        val number = calculateNumberOfVariations(n, m)
        if (number > BigInteger.valueOf(Long.MAX_VALUE)) {
            throw IllegalArgumentException("Value is too big")
        } else {
            return number.toLong()
        }
    }

    private fun calculateNumberOfVariations(n: Int, m: Int): BigInteger {
        val numerator = factorial(n)
        val denominator = factorial(n - m)
        return numerator.divide(denominator)
    }

    fun calculateNumberOfPermutationsInt(n: Int): Int {
        val f = factorial(n)
        if (f > BigInteger.valueOf(Int.MAX_VALUE.toLong())) {
            throw IllegalArgumentException("Value is too big")
        } else {
            return f.toInt()
        }
    }

    fun calculateNumberOfPermutationsLong(n: Int): Long {
        val f = factorial(n)
        if (f > BigInteger.valueOf(Long.MAX_VALUE)) {
            throw IllegalArgumentException("Value is too big")
        } else {
            return f.toLong()
        }
    }

    private fun factorial(n: Int): BigInteger {
        var result = BigInteger.ONE
        for (i in 1..n) {
            result = result.multiply(BigInteger.valueOf(i.toLong()))
        }
        return result
    }


    /**
     * Provides all permutations of the elements in the given array.
     * recursive implementation, uses list invariant
     * returns array of arrays
     */
    fun <T> getAllPermutations(elements: Array<T>): Array<List<T>> {
        if (elements.isEmpty()) {
            return arrayOf()
        }

        val result = MutableList(0) { listOf<T>() }
        val used = BooleanArray(elements.size)

        collectAllPermutations(result, used, elements, arrayListOf())

        return result.toTypedArray()
    }

    private fun <T> collectAllPermutations(result: MutableList<List<T>>, used: BooleanArray, elements: Array<T>, selection: MutableList<T>) {
        if (selection.size == elements.size) {
            result.add(selection.toList())
        } else {
            for (i in used.indices) {
                if (!used[i]) {
                    val usedCopy = used.clone()
                    usedCopy[i] = true
                    val selectionCopy = selection.toMutableList()
                    selectionCopy.add(elements[i])
                    collectAllPermutations(result, usedCopy, elements, selectionCopy)
                }
            }
        }
    }

    data class CombinationsInvariant<T>(val value: Array<List<T>>, var index: Int) {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as CombinationsInvariant<*>

            if (!value.contentEquals(other.value)) return false
            if (index != other.index) return false

            return true
        }

        override fun hashCode(): Int {
            var result = value.contentHashCode()
            result = 31 * result + index
            return result
        }
    }
}