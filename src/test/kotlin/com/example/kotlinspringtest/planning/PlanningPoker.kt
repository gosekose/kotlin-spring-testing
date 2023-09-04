package com.example.kotlinspringtest.planning

class PlanningPoker {

    fun identifyExtremes(estimates: List<Estimate>): List<String> {

        verifyPossibleExtremes(estimates)

        var lowerEstimate = estimates[0]
        var highestEstimate = estimates[0]

        estimates.forEach {
            if (it.estimate <= lowerEstimate.estimate) lowerEstimate = it
            if (it.estimate >= highestEstimate.estimate) highestEstimate = it
        }

        return listOf(
            lowerEstimate.developer,
            highestEstimate.developer,
        )
    }

    private fun verifyPossibleExtremes(estimates: List<Estimate>) {
        if (estimates.size <= 1) throw IllegalArgumentException()
    }
}

data class Estimate(
    val estimate: Double,
    val developer: String,
)