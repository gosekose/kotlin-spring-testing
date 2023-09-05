package com.example.kotlinspringtest.specificationbased

import java.io.*

fun main() = BufferedReader(InputStreamReader(System.`in`)).use {
    val bookSoldReporter = BookSoldReporter()
    val inputCount = readlnOrNull()?.toInt() ?: 0

    repeat(inputCount) {
        val bookName = readlnOrNull() ?: ""
        bookSoldReporter.saveBookSoldRecordAndUpdateBestSeller(bookName)
    }

    println(bookSoldReporter.bestSeller)
}

class BookSoldReporter {
    private val bookSoldCountMap = mutableMapOf<String, Int>()
    var bestSeller: BestSeller? = null
        private set

    fun saveBookSoldRecordAndUpdateBestSeller(bookName: String, soldCount: Int = 1) {
        val totalSoldCount = bookSoldCountMap.getOrDefault(bookName, 0) + soldCount
        bookSoldCountMap[bookName] = totalSoldCount
        bestSeller = bestSeller?.getUpdateBestSeller(bookName, totalSoldCount) ?: BestSeller(bookName, totalSoldCount)
    }

    fun getBookSoldRecord(bookName: String): Int {
        return bookSoldCountMap[bookName] ?: 0
    }
}

data class BestSeller(
    val bookName: String,
    val soldCount: Int,
) {
    fun getUpdateBestSeller(otherBookName: String, otherSoldCount: Int): BestSeller {
        return when {
            soldCount < otherSoldCount -> BestSeller(otherBookName, otherSoldCount)
            soldCount == otherSoldCount && otherBookName < bookName -> BestSeller(otherBookName, otherSoldCount)
            else -> this
        }
    }
}