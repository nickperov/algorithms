package com.nickperov.stud.combinatorics

import com.nickperov.stud.combinatorics.CombinatorialUtils.getAllPermutations

const val ALPHABET = "abcdefghijklmnopqrstuvwxyz"
const val DIGITS = "0123456789"

fun main() {
    // printAllPossibleStringsSample()
    // printAllPermutationsSample()
}

fun printAllPossibleStringsSample() {
    generateWords(0, ALPHABET.toCharArray(), CharArray(4))
}

fun printAllPermutationsSample() {
    printAllPermutations(arrayOf('A', 'B', 'C', 'D', 'E'))
}


fun generateWords(currIndex: Int, charset: CharArray, word: CharArray) {
    if (currIndex == word.size) {
        println(String(word))
        return
    }

    for (character in charset) {
        val wordCopy = word.clone()
        wordCopy[currIndex] = character
        generateWords(currIndex + 1, charset, wordCopy)
    }
}

fun printAllPermutations(elements: Array<Char>) {
    getAllPermutations(elements).forEach {
        println(it.joinToString(""))
    }
}