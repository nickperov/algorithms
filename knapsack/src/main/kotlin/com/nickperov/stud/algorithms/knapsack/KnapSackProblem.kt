package com.nickperov.stud.algorithms.knapsack

import java.lang.RuntimeException
import kotlin.random.Random

data class KnapSack(val weightLimit: Int)

data class Item(val weight: Int, val price: Int)

data class ProblemParameters(val knapSackSize: Int, val itemListSize: Int, val itemWeightLimit: Int, val itemPriceLimit: Int)

data class Problem(val knapSack: KnapSack, val items: List<Item>)

// Default values
const val knapSackDefaultSize = 100
const val itemListDefaultSize = 50
const val itemWeightDefaultLimit = 10
const val itemPriceDefaultLimit = 20

fun main(args: Array<String>) {

    val problem = initProblemFromArgs(args)
    printItems(problem.items)
}

private fun initProblemFromArgs(args: Array<String>): Problem {
    println("Init from comand line arguments")
    val (knapSackSize, itemListSize, itemWeightLimit, itemPriceLimit) = initParameters(args)
    println("Knapsack size: $knapSackSize; product list size: $itemListSize; product limits: (weight: $itemWeightLimit, price: $itemPriceLimit)")

    val knapSack = KnapSack(knapSackSize)
    val items = initItems(itemListSize, itemWeightLimit, itemPriceLimit)

    return Problem(knapSack, items)
}


private fun initParameters(args: Array<String>): ProblemParameters {
    return if (args.isNotEmpty()) {
        if (args.size != 4)
            throw RuntimeException("Wrong number of arguments")

        ProblemParameters(args[0].toInt(), args[1].toInt(), args[2].toInt(), args[3].toInt())
    } else {
        println("Command line arguments are empty, use default values")
        ProblemParameters(knapSackDefaultSize, itemListDefaultSize, itemWeightDefaultLimit, itemPriceDefaultLimit)
    }
}

private fun printItems(items: List<Item>) {
    val itemPrintFormat = "%5s %10s %10s"
    println("Items list")
    println("----------------------------------------------")
    print("|")
    println(itemPrintFormat.format("Number", "Weight", "Price"))
    println("----------------------------------------------")
    items.forEachIndexed { index, item -> print("|"); println(itemPrintFormat.format(index, item.weight, item.price)) }
}

private fun initItems(number: Int, weightLimit: Int, priceLimit: Int): List<Item> {
    return generateSequence(0, { i -> i + 1 }).take(number).map { initItem(weightLimit, priceLimit) }.toList()
}

private fun initItem(weightLimit: Int, priceLimit: Int) = Item(Random.nextInt(weightLimit), Random.nextInt(priceLimit))

