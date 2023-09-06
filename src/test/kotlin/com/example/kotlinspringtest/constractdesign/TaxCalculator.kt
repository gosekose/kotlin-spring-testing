package com.example.kotlinspringtest.constractdesign

class TaxCalculator {
    fun calculateTax(amount: Double): Double {

        require(amount >= 0) { throw TaxException(TaxErrorCode.INPUT_NOT_NEGATIVE)}

        val taxAmount = amount * RATE

        require(taxAmount >= 0) { throw TaxException(TaxErrorCode.OUTPUT_NOT_NEGATIVE)}

        return taxAmount
    }

    companion object {
        const val RATE = 1.1
    }
}

class TaxException(
    private val errorCode: TaxErrorCode
) : RuntimeException() {
    override val message: String
        get() = errorCode.message
}

enum class TaxErrorCode(
    val code: String,
    val message: String,
) {
    INPUT_NOT_NEGATIVE("400", "Amount cannot be negative."),
    OUTPUT_NOT_NEGATIVE("500", "Calculated tax amount cannot be negative.");
}
