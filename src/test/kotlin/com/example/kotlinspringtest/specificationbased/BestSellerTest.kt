package com.example.kotlinspringtest.specificationbased

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class BestSellerTest : BehaviorSpec({

    given("현재 베스트셀러 책의 이름과 수량이 주어져요") {
        val bestSeller = BestSeller("GoseBook", 12)


        `when`("비교할 책의 수량이 베스트셀러와 같고, 이름이 같을 때") {
            val otherBookName = "GoseBook"
            val otherSoldCount = 12
            val result = bestSeller.getUpdateBestSeller(otherBookName, otherSoldCount)

            then("현재 베스트셀러가 유지되어야 해요") {
                result shouldBe bestSeller
            }
        }

        `when`("비교할 책의 수량이 베스트셀러와 같고, 이름이 빠를 때") {
            val otherBookName = "CoseBook"
            val otherSoldCount = 12
            val result = bestSeller.getUpdateBestSeller(otherBookName, otherSoldCount)

            then("베스트셀러는 비교한 책으로 바뀌어야 해요") {
                result shouldNotBe bestSeller
                result.bookName shouldBe otherBookName
                result.soldCount shouldBe otherSoldCount
            }
        }

        `when`("비교할 책의 수량이 베스트셀러와 같고, 이름은 느릴 때") {
            val otherBookName = "KoseBook"
            val otherSoldCount = 12
            val result = bestSeller.getUpdateBestSeller(otherBookName, otherSoldCount)

            then("현재 베스트셀러가 유지되어야 해요") {
                result shouldBe bestSeller
            }
        }

        `when`("비교할 책의 수량이 베스트셀러보다 적고, 이름은 다를 떄") {
            val otherBookName = "KoseBook"
            val otherSoldCount = 11
            val result = bestSeller.getUpdateBestSeller(otherBookName, otherSoldCount)

            then("현재 베스트셀러가 유지되어야 해요") {
                result shouldBe bestSeller
            }
        }

        `when`("비교할 책의 수량이 베스트셀러보다 많고, 이름은 다를 떄") {
            val otherBookName = "KoseBook"
            val otherSoldCount = 13
            val result = bestSeller.getUpdateBestSeller(otherBookName, otherSoldCount)

            then("베스트셀러는 비교한 책으로 바뀌어야 해요") {
                result shouldNotBe bestSeller
                result.bookName shouldBe otherBookName
                result.soldCount shouldBe otherSoldCount
            }
        }
    }
})