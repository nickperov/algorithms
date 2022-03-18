package com.nickperov.stud.algorithms.montyhall

import kotlin.random.Random

enum class Prize {
    GOAT, CAR
}

enum class Strategy {
    KEEP, CHANGE, GUESS
}

object MontyHallApp {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Simulate monty hall game with different strategies")
        val rounds = generateRounds(1_000_000)
        /*println(rounds.contentDeepToString())*/
        Strategy.values().forEach { strategy ->
            val percentageWins = play(rounds, strategy)
            println("Strategy %{strategy.name} wins:$percentageWins%")
        }
    }
}


private fun play(rounds: Array<Array<Prize>>, strategy: Strategy): Double {
    val result = rounds.map { play(it, strategy) }.toTypedArray()
    val totalWins = result.filter { it }.count()
    return (totalWins / (rounds.size / 100).toDouble()).toDouble()
}

private fun play(round: Array<Prize>, strategy: Strategy): Boolean {
    val firstTry = Random.nextInt(3)
    val openedDoor = openDoor(round, firstTry)
    val secondTry = makeSecondTry(firstTry, openedDoor, strategy)
    return round[secondTry] == Prize.CAR
}

private fun makeSecondTry(firstTry: Int, openedDoor: Int, strategy: Strategy): Int {
    return when (strategy) {
        Strategy.KEEP -> firstTry
        Strategy.CHANGE -> nextDoor(firstTry, openedDoor)
        Strategy.GUESS -> if (Random.nextBoolean()) nextDoor(firstTry, openedDoor) else firstTry
    }
}

private fun nextDoor(currentDoor: Int, openedDoor: Int): Int {
    for (i in 0..2) {
        if (i != currentDoor && i != openedDoor) {
            return i;
        }
    }
    throw RuntimeException("Fatal error")
}

private fun openDoor(round: Array<Prize>, firstTry: Int): Int {
    round.forEachIndexed { index, prize ->
        if (index != firstTry && prize == Prize.GOAT) {
            return index
        }
    }
    throw RuntimeException("Fatal error")
}

private fun generateRounds(size: Int): Array<Array<Prize>> {
    return Array(size) { generateRound() }
}

private fun generateRound(): Array<Prize> {
    return when (Random.nextInt(3)) {
        0 -> {
            arrayOf(Prize.CAR, Prize.GOAT, Prize.GOAT)
        }
        1 -> {
            arrayOf(Prize.GOAT, Prize.CAR, Prize.GOAT)
        }
        else -> {
            arrayOf(Prize.GOAT, Prize.GOAT, Prize.CAR)
        }
    }
}
