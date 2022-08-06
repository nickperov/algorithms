package com.nickperov.stud.combinatorics.experiment

import com.nickperov.stud.combinatorics.CombinatorialUtils
import java.util.*

/**
 * Test sample to try different approaches to calculate permutations
 */
object PermutationsCalculatorExperiment {
    /**
     * Provides all permutations of the elements in the given array.
     * recursive implementation, uses array invariant of pre-calculated size
     * returns array of arrays
     */
    @JvmStatic
    fun <T> getAllPermutationsV1(elements: Array<T>): Array<List<T>> {
        if (elements.isEmpty()) {
            return arrayOf()
        }

        val result = CombinatorialUtils.CombinationsInvariant(Array(CombinatorialUtils.calculateNumberOfPermutationsInt(elements.size)) { listOf<T>() }, 0)
        val used = BooleanArray(elements.size)

        collectAllPermutations(result, used, elements, arrayListOf())

        return result.value
    }

    private fun <T> collectAllPermutations(result: CombinatorialUtils.CombinationsInvariant<T>, used: BooleanArray, elements: Array<T>, selection: MutableList<T>) {
        if (selection.size == elements.size) {
            result.value[result.index] = selection.toList()
            result.index += 1
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

    /**
     * Provides all permutations of the elements in the given array.
     * recursive implementation, uses list invariant
     * returns array of arrays
     * 
     * Default one - look into utils class
     */
    @JvmStatic
    fun <T> getAllPermutationsV2(elements: Array<T>): Array<List<T>> {
        return CombinatorialUtils.getAllPermutations(elements)
    }

    /**
     * Provides all permutations of the elements in the given array.
     * recursive implementation, optimized (reduced number of array copy operations), uses array invariant of pre-calculated size
     * returns array of arrays
     */
    @JvmStatic
    fun <T> getAllPermutationsV3(elements: Array<T>): Array<List<T>> {
        if (elements.isEmpty()) {
            return arrayOf()
        }

        val result = CombinatorialUtils.CombinationsInvariant(Array(CombinatorialUtils.calculateNumberOfPermutationsInt(elements.size)) { listOf<T>() }, 0)
        val used = BooleanArray(elements.size)

        collectAllPermutations(result, used, elements, arrayListOf(), used.size)

        return result.value
    }

    private fun <T> collectAllPermutations(result: CombinatorialUtils.CombinationsInvariant<T>, used: BooleanArray, elements: Array<T>, selection: MutableList<T>, count: Int) {
        if (selection.size == elements.size) {
            result.value[result.index] = selection.toList()
            result.index += 1
        } else {
            var k = 1
            for (i in used.indices) {
                if (!used[i]) {
                    if (k++ == count) {
                        // Do not copy arrays
                        used[i] = true
                        selection.add(elements[i])
                        collectAllPermutations(result, used, elements, selection, count - 1)
                    } else {
                        val usedCopy = used.clone()
                        usedCopy[i] = true
                        val selectionCopy = selection.toMutableList()
                        selectionCopy.add(elements[i])
                        collectAllPermutations(result, usedCopy, elements, selectionCopy, count - 1)
                    }
                }
            }
        }
    }

    /**
     * Provides all permutations of the elements in the given array.
     * non-recursive implementation, optimized (reduced number of array copy operations), uses array of pre-calculated size
     * returns array of arrays
     */
    @JvmStatic
    fun <T> getAllPermutationsV4(elements: Array<T>): Array<List<T>> {
        if (elements.isEmpty()) {
            return arrayOf()
        }

        val result = CombinatorialUtils.CombinationsInvariant(Array(CombinatorialUtils.calculateNumberOfPermutationsInt(elements.size)) { listOf<T>() }, 0)

        val stack: Deque<RecursiveState<T>> = ArrayDeque(elements.size)
        var state: RecursiveState<T>? = RecursiveState(BooleanArray(elements.size), arrayListOf(), elements.size, 0, 1)
        stack.push(state)

        MAIN_LOOP@
        while (true) {
            if (state == null) {
                break
            } else {
                val used = state.used
                val selection = state.selection
                val count = state.count

                var k = state.currCount
                var i = state.index

                while (i < elements.size) {
                    if (!used[i]) {
                        if (k++ == count) {
                            // Do not copy arrays
                            used[i] = true
                            selection.add(elements[i])

                            // Update state index
                            state?.index = i + 1
                            state?.currCount = k

                            if (selection.size == elements.size) {
                                // Save the result
                                result.value[result.index] = selection.toList()
                                result.index += 1
                                break
                            }

                            state = RecursiveState(used, selection, count - 1, 0, 1)
                            stack.push(state)
                            continue@MAIN_LOOP
                        } else {
                            val usedCopy = used.clone()
                            usedCopy[i] = true
                            val selectionCopy = selection.toMutableList()
                            selectionCopy.add(elements[i])

                            // Update state index and count
                            state?.index = i + 1
                            state?.currCount = k

                            state = RecursiveState(usedCopy, selectionCopy, count - 1, 0, 1)
                            stack.push(state)
                            continue@MAIN_LOOP
                        }
                    }

                    i++
                    // Update state index and count
                    state?.index = i
                    state?.currCount = k
                }
                stack.remove()
                state = stack.peek()
            }
        }

        return result.value
    }

    data class RecursiveState<T>(val used: BooleanArray, val selection: MutableList<T>, val count: Int, var index: Int, var currCount: Int) {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as RecursiveState<*>

            if (!used.contentEquals(other.used)) return false
            if (selection != other.selection) return false
            if (count != other.count) return false
            if (index != other.index) return false
            if (currCount != other.currCount) return false

            return true
        }

        override fun hashCode(): Int {
            var result = used.contentHashCode()
            result = 31 * result + selection.hashCode()
            result = 31 * result + count
            result = 31 * result + index
            result = 31 * result + currCount
            return result
        }
    }
}
