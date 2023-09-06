package com.example.kotlinspringtest.constractdesign

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class TaxCalculatorTest : FunSpec({

    val taxCalculator = TaxCalculator()

    context("사전 조건: 입력이 음수가 입력되면") {
        test("TaxException이 발생하고 INPUT_NOT_NEGATIVE 메세지가 발생해요") {
            shouldThrow<TaxException> {
                taxCalculator.calculateTax(-1.0)
            }.message.shouldBe(TaxErrorCode.INPUT_NOT_NEGATIVE.message)
        }
    }

    context("사후 조건: 결과가 음수가 나오면") {
        test("TaxException이 발생하고 OUTPUT_NOT_NEGATIVE 메세지가 발생해요") {
            val result = -1
            shouldThrow<TaxException> {
                require(result >= 0) {throw TaxException(TaxErrorCode.OUTPUT_NOT_NEGATIVE)}
            }.message.shouldBe(TaxErrorCode.OUTPUT_NOT_NEGATIVE.message)
        }
    }

})