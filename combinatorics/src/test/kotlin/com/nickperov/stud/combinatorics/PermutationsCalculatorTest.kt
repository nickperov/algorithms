package com.nickperov.stud.combinatorics

import com.nickperov.stud.combinatorics.experiment.PermutationsCalculatorExperiment
import org.junit.Assert.assertArrayEquals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class PermutationsCalculatorTest {

    @Test
    fun test01() {
        val letters = arrayOf('a', 'b', 'c')

        val resultV1 = PermutationsCalculatorExperiment.getAllPermutationsV1(letters)
        val resultV2 = PermutationsCalculatorExperiment.getAllPermutationsV2(letters)
        val resultV3 = PermutationsCalculatorExperiment.getAllPermutationsV3(letters)
        val resultV4 = PermutationsCalculatorExperiment.getAllPermutationsV4(letters)

        assertEquals(6, resultV1.size)
        assertEquals(6, resultV2.size)
        assertEquals(6, resultV3.size)
        assertEquals(6, resultV4.size)

        Assertions.assertArrayEquals(resultV1, resultV2)
        Assertions.assertArrayEquals(resultV1, resultV3)
        Assertions.assertArrayEquals(resultV1, resultV4)

        val permutationsSet = resultV1.toSet()

        val allPossiblePermutations = listOf("abc", "acb", "bac", "bca", "cab", "cba")

        allPossiblePermutations.forEach {
            assertTrue(permutationsSet.contains(it.toList()))
        }
    }

    @Test
    fun test02() {
        val letters = arrayOf('A', 'B', 'C', 'D')

        val resultV1 = PermutationsCalculatorExperiment.getAllPermutationsV1(letters)
        val resultV2 = PermutationsCalculatorExperiment.getAllPermutationsV2(letters)
        val resultV3 = PermutationsCalculatorExperiment.getAllPermutationsV3(letters)
        val resultV4 = PermutationsCalculatorExperiment.getAllPermutationsV4(letters)


        assertEquals(24, resultV1.size)
        assertEquals(24, resultV2.size)
        assertEquals(24, resultV3.size)

        Assertions.assertArrayEquals(resultV1, resultV2)
        Assertions.assertArrayEquals(resultV1, resultV3)
        Assertions.assertArrayEquals(resultV1, resultV4)

        val permutationsSet = resultV1.toSet()

        val allPossiblePermutations = listOf(
            "ABCD", "ACBD", "ADBC", "ADCB", "ABDC", "ACDB",
            "BACD", "BCAD", "BDCA", "BDAC", "BCDA", "BADC",
            "CABD", "CADB", "CDBA", "CBAD", "CDAB", "CBDA",
            "DABC", "DACB", "DBAC", "DCAB", "DBCA", "DCBA"
        )

        allPossiblePermutations.forEach {
            assertTrue(permutationsSet.contains(it.toList()))
        }
    }

    @Test
    fun test03() {
        val emptyElements = emptyArray<Char>()

        val resultV1 = PermutationsCalculatorExperiment.getAllPermutationsV1(emptyElements)
        val resultV2 = PermutationsCalculatorExperiment.getAllPermutationsV2(emptyElements)
        val resultV3 = PermutationsCalculatorExperiment.getAllPermutationsV3(emptyElements)
        val resultV4 = PermutationsCalculatorExperiment.getAllPermutationsV4(emptyElements)

        assertEquals(0, resultV1.size)
        assertEquals(0, resultV2.size)
        assertEquals(0, resultV3.size)
        assertEquals(0, resultV4.size)
    }
}