package com.example.kotlinspringtest.constractdesign

class TaxCalculator {
    fun calculateTax(amount: Double, countryTax: CountryTax): Double {

        require(amount >= 0) { throw TaxException(TaxErrorCode.INPUT_NOT_NEGATIVE)}

        val taxAmount = amount * countryTax.rate

        require(taxAmount >= 0) { throw TaxException(TaxErrorCode.OUTPUT_NOT_NEGATIVE)}

        return taxAmount
    }
}

enum class Country(
    val countryName: String,
    val countryCode: String,
) {
    KOREA("Korea", "100"),
    USA("United State America", "101");
}

enum class CountryTax(
    val country: Country,
    val rate: Double,
) {
    KOREA(Country.KOREA, 1.1),
    USA(Country.USA, 1.2);

    val countryCode: String
        get() = country.countryCode

    val countryName: String
        get() = country.countryName
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
