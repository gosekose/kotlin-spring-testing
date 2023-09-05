package com.example.kotlinspringtest.specificationbased

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class BestSellerTest : BehaviorSpec({

    given("현재 베스트셀러 책의 이름과 수량이 주어져요") {
        val bestSeller = BestSeller("GoseBook", 12)


        `when`("비교할 책의 이름이 다르고, 수량이 베스트셀러 책보다 작을 때") {
            val otherBookName = "KoseBook"
            val otherSoldCount = 11
            val result = bestSeller.getUpdateBestSeller(otherBookName, otherSoldCount)

            then("현재 베스트셀러가 유지되어야 해요") {
                result shouldBe bestSeller
            }
        }

        `when`("비교할 책의 이름이 다르고, 수량이 베스트셀러 책보다 많을 때") {
            val otherBookName = "KoseBook"
            val otherSoldCount = 13
            val result = bestSeller.getUpdateBestSeller(otherBookName, otherSoldCount)

            then("현재 베스트셀러가 비교할 책으로 바뀌어야 해요") {
                result shouldNotBe bestSeller
                result.bookName shouldBe otherBookName
                result.soldCount shouldBe otherSoldCount
            }
        }

        `when`("비교할 책의 이름은 사전순으로 느리고, 수량이 베스트셀러와 같을 떄") {
            val otherBookName = "KoseBook"
            val otherSoldCount = 12
            val result = bestSeller.getUpdateBestSeller(otherBookName, otherSoldCount)

            then("현재 베스트셀러가 유지되어야 해요") {
                result shouldBe bestSeller
            }
        }

        `when`("비교할 책의 이름은 사전순으로 빠르고, 수량이 베스트셀러와 같을 떄") {
            val otherBookName = "CoseBook"
            val otherSoldCount = 12
            val result = bestSeller.getUpdateBestSeller(otherBookName, otherSoldCount)

            then("현재 베스트셀러가 비교할 책으로 바뀌어야 해요") {
                result shouldNotBe bestSeller
                result.bookName shouldBe otherBookName
                result.soldCount shouldBe otherSoldCount
            }
        }

        `when`("비교할 책의 수량이 베스트셀러와 모두 같을 떄") {
            val otherBookName = "KoseBook"
            val otherSoldCount = 12
            val result = bestSeller.getUpdateBestSeller(otherBookName, otherSoldCount)

            then("현재 베스트셀러가 유지되어야 해요") {
                result shouldBe bestSeller
            }
        }
    }
})