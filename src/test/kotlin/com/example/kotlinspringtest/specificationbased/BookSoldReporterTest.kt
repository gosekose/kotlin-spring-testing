package com.example.kotlinspringtest.specificationbased

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class BookSoldReporterTest : BehaviorSpec({

    isolationMode = IsolationMode.InstancePerLeaf

    given("베스트셀러가 비어있어요") {

        val bookSoldReporter = BookSoldReporter()

        `when`("판매한 책을 저장해요") {
            val bookName = "GoseBook"
            bookSoldReporter.saveBookSoldRecordAndUpdateBestSeller(bookName)

            val soldCountResult = bookSoldReporter.getBookSoldCount(bookName)
            val bestSellerResult = bookSoldReporter.bestSeller

            then("판매한 책의 수량은 1, 베스트셀러가 되어야 해요") {
                soldCountResult shouldBe 1
                bestSellerResult.shouldNotBeNull()
                bestSellerResult.bookName shouldBe bookName
                bestSellerResult.soldCount shouldBe 1
            }
        }

        `when`("판매한 책을 여러 번 저장해요") {
            val bookName = "GoseBook"
            bookSoldReporter.saveBookSoldRecordAndUpdateBestSeller(bookName)
            bookSoldReporter.saveBookSoldRecordAndUpdateBestSeller(bookName)

            val soldCountResult = bookSoldReporter.getBookSoldCount(bookName)
            val bestSellerResult = bookSoldReporter.bestSeller

            then("판매한 책의 수량은 2, 베스트셀러가 되어야 해요") {
                soldCountResult shouldBe 2
                bestSellerResult.shouldNotBeNull()
                bestSellerResult.bookName shouldBe bookName
                bestSellerResult.soldCount shouldBe 2
            }
        }
    }

    given("베스트셀러에 책이 등록되어 있어요") {

        val bookSoldReporter = BookSoldReporter()
        bookSoldReporter.saveBookSoldRecordAndUpdateBestSeller("GoseBook")


        `when`("베스트셀러가 아닌 판매된 다른 책 2권을 저장해요") {

            val bookName = "KoseBook"
            bookSoldReporter.saveBookSoldRecordAndUpdateBestSeller(bookName, 2)

            val soldCountResult = bookSoldReporter.getBookSoldCount(bookName)
            val bestSellerResult = bookSoldReporter.bestSeller

            then("판매한 책의 수량은 2, 베스트셀러는 다른 책이 되어야 해요") {
                soldCountResult shouldBe 2
                bestSellerResult.shouldNotBeNull()
                bestSellerResult.bookName shouldBe bookName
                bestSellerResult.soldCount shouldBe 2
            }
        }
    }
})