package com.example.kotlinspringtest.planning

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PlanningPokerTest : FunSpec({

    val planningPoker = PlanningPoker()

    context("리스트가 비어있는 경우") {
        val estimates = emptyList<Estimate>()

        test("IllegalArgumentException 예외가 발생한다.") {
            shouldThrow<IllegalArgumentException> {
                planningPoker.identifyExtremes(estimates)
            }
        }
    }

    context("리스트가 1개만 있는 경우") {
        val estimates = listOf(Estimate(100.0, "gose"))

        test("IllegalArgumentException 예외가 발생한다.") {
            shouldThrow<IllegalArgumentException> {
                planningPoker.identifyExtremes(estimates)
            }
        }
    }

    context("리스트가 2개 존재하되, 두 값이 같은 경우") {
        val estimates = listOf(
            Estimate(100.0, "gose"),
            Estimate(100.0, "kose"),
        )

        test("kose가 가장 작은 estimate이자 가장 큰 estimate가 된다.") {
            val result = planningPoker.identifyExtremes(estimates)

            result[0] shouldBe "kose"
            result[1] shouldBe "kose"
        }
    }

    context("리스트가 2개 존재하되, 두 값이 다른 경우") {
        val estimates = listOf(
            Estimate(100.0, "gose"),
            Estimate(80.0, "kose"),
        )

        test("kose가 가장 작은 estimate, gose가 가장 큰 estimate가 된다.") {
            val result = planningPoker.identifyExtremes(estimates)

            result[0] shouldBe "kose"
            result[1] shouldBe "gose"
        }
    }

    context("리스트가 2개 이상 존재하는 경우") {
        val estimates = listOf(
            Estimate(100.0, "gose"),
            Estimate(80.0, "kose"),
            Estimate(100.0, "gosekose"),
        )

        test("kose는 가장 작은 estimate, gosekose가 가장 큰 estimate가 된다.") {
            val result = planningPoker.identifyExtremes(estimates)

            result[0] shouldBe "kose"
            result[1] shouldBe "gosekose"
        }
    }

    context("속성 기반으로 2개 이상의 입력이 주어지는 경우를 테스트를 진행할 때") {
        test("가장 작은 estimate와 가장 큰 estimate가 출력되어야 한다") {
            TODO()
        }
    }
})