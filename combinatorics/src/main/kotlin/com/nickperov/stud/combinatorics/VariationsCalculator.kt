package com.nickperov.stud.combinatorics

/**
 * Test sample to try different approaches to calculate variations (ordered samples without replacement).
 */
object VariationsCalculator {

    @JvmStatic
    fun <T> getAllVariationsV1(elements: Array<T>): Array<List<T>> {
        if (elements.isEmpty()) {
            return arrayOf()
        }
    
        //val result = CombinatorialUtils.CombinationsInvariant(Array(CombinatorialUtils.calculateNumberOfVariationsInt(elements.size)) { listOf<T>() }, 0)
        //TODO
        /*val used = BooleanArray(elements.size)

        PermutationsCalculator.collectAllPermutations(result, used, elements, arrayListOf())*/

        //return result.value
        return arrayOf()
    }


}