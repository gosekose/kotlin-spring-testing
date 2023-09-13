package com.example.kotlinspringtest.testdouble

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockkObject
import java.time.LocalDate

class ChristmasDiscountPolicyTest : BehaviorSpec({
    isolationMode = IsolationMode.InstancePerLeaf

    val sut = ChristmasDiscountPolicy()
    mockkObject(ClockUtil)

    given("상품 가격이 주어져요") {

        val amount = 1000.0

        `when`("날짜가 크리스마스 일 때") {
            every { ClockUtil.now() } returns LocalDate.parse("2023-12-25")

            val result = sut.apply(amount)
            then("상품은 할인을 받은 가격이어야해요") {
                result shouldBe amount - (amount * ChristmasDiscountPolicy.DISCOUNT_RATE)
            }
        }

        `when`("크리스마스가 아니에요 (달이 다른 경우)") {
            every { ClockUtil.now() } returns LocalDate.parse("2023-11-25")

            val result = sut.apply(amount)
            then("상품은 기본가여야 해요") {
                result shouldBe amount
            }
        }

        `when`("크리스마스가 아니에요 (날이 다른 경우)") {
            every { ClockUtil.now() } returns LocalDate.parse("2023-12-24")

            val result = sut.apply(amount)
            then("상품은 기본가여야 해요") {
                result shouldBe amount
            }
        }
    }
})