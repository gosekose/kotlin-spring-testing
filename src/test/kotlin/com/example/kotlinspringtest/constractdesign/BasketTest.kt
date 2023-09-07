package com.example.kotlinspringtest.constractdesign

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.shouldBe

class BasketTest : BehaviorSpec({

    isolationMode = IsolationMode.InstancePerLeaf

    val book = Product(
        id = 1L,
        name = "Book",
        price = 3000,
        stock = 1000,
    )

    val note = Product(
        id = 2L,
        name = "Note",
        price = 1000,
        stock = 1000,
    )

    given("장바구니에 상품을 추가하기 위해 생성해요") {
        val basket = Basket("gosekose")

        `when`("장바구니에 재고를 넘지 않는 수량으로 책을 추가해요") {
            val requiredQuantity = 1000L
            basket.add(book, requiredQuantity)

            val basketItems = basket.getBasketItem()
            then("장바구니에 정상 추가되고, 총 장바구니 금액이 증가해요") {
                basketItems.size shouldBe 1
                basketItems[0].name shouldBe book.name
                basketItems[0].price shouldBe book.price
                basketItems[0].quantity shouldBe requiredQuantity
                basket.totalPrice shouldBe book.price * requiredQuantity
            }
        }

        `when`("장바구니에 재고를 넘는 수량으로 책을 추가하면") {
            val requiredQuantity = 1001L

            then("재고 부족 예외가 발생 해야해요") {
                val exception = shouldThrow<BasketException> {
                    basket.add(book, requiredQuantity)
                }

                exception.cause.message shouldBe BasketErrorCode.SHORTAGE_STOCK.code
                exception.message shouldBe BasketErrorCode.SHORTAGE_STOCK.message
            }
        }

        `when`("장바구니에 음수를 추가하면") {
            val requiredQuantity = -1L

            then("음수 불가 예외가 발생 해야해요") {
                val exception = shouldThrow<BasketException> {
                    basket.add(book, requiredQuantity)
                }

                exception.cause.message shouldBe BasketErrorCode.INPUT_QUANTITY_NOT_NEGATIVE.code
                exception.message shouldBe BasketErrorCode.INPUT_QUANTITY_NOT_NEGATIVE.message
            }
        }

        `when`("장바구니에 재고를 넘지 않는 수량으로 책을 업데이트 해요") {
            val firstRequiredQuantity = 100L
            basket.add(book, firstRequiredQuantity)

            val secondRequiredQuantity = 200L
            basket.updateQuantity(book, secondRequiredQuantity)

            val basketItems = basket.getBasketItem()
            then("장바구니에 정상 추가되고, 총 장바구니 금액이 증가해요") {
                basketItems.size shouldBe 1
                basketItems[0].name shouldBe book.name
                basketItems[0].price shouldBe book.price
                basketItems[0].quantity shouldBe secondRequiredQuantity
                basket.totalPrice shouldBe book.price * secondRequiredQuantity
            }
        }

        `when`("장바구니에 재고를 넘지 않는 수량으로 책과 노트를 추가해요") {
            val bookRequiredQuantity = 100L
            val noteRequiredQuantity = 200L

            basket.add(book, bookRequiredQuantity)
            basket.add(note, noteRequiredQuantity)

            val basketItems = basket.getBasketItem()
            then("장바구니에 정상 추가되고, 총 장바구니 금액이 증가해요") {
                basketItems.size shouldBe 2
                basketItems[0].name shouldBeIn arrayOf(book.name, note.name)
                basketItems[0].price shouldBeIn arrayOf(book.price, note.price)
                basket.totalPrice shouldBe book.price * bookRequiredQuantity + note.price * noteRequiredQuantity
            }
        }
    }

    given("장바구니에서 상품이 주어져 있어요") {
        val basket = Basket("goseKose")

        val requiredQuantity = 200L
        basket.add(book, requiredQuantity)

        `when`("장바구니에서 상품을 제거하면") {
            basket.remove(book)

            val basketItems = basket.getBasketItem()
            then("장바구니에서 상품이 제거되어야 해요, 총 장바구니 금액이 감소해요") {
                basketItems.size shouldBe 0
            }
        }

        `when`("장바구니에서 저장된 상품 수량을 1개 이상으로 바꾸면") {
            val requiredChangedQuantity = 1L
            basket.updateQuantity(book, requiredChangedQuantity)

            val basketItems = basket.getBasketItem()
            then("장바구니에 저장된 상품 수량이 줄어들고, 총 장바구니 금액이 감소해요") {
                basketItems.size shouldBe 1
                basketItems[0].quantity shouldBe requiredChangedQuantity
                basket.totalPrice shouldBe book.price * requiredChangedQuantity
            }
        }

        `when`("장바구니에서 저장된 상품 수량을 0으로 바꾸면") {
            val requiredChangedQuantity = 0L
            basket.updateQuantity(book, requiredChangedQuantity)

            val basketItems = basket.getBasketItem()
            then("장바구니에 저장된 상품 삭제되고, 총 장바구니 금액이 감소해요") {
                basketItems.size shouldBe 0
                basket.totalPrice shouldBe book.price * requiredChangedQuantity
            }
        }

        `when`("장바구니에서 저장된 상품 수량을 -1으로 바꾸면") {
            val requiredChangedQuantity = -1L

            then("음수 불가 예외가 발생해요") {
                val exception = shouldThrow<BasketException> {
                    basket.updateQuantity(book, requiredChangedQuantity)
                }

                exception.cause.message shouldBe BasketErrorCode.INPUT_QUANTITY_NOT_NEGATIVE.code
                exception.message shouldBe BasketErrorCode.INPUT_QUANTITY_NOT_NEGATIVE.message
            }
        }
    }

    given("유저가 장바구니에 상품을 담았는데, 다른 유저가 상품 주문을 완료하여 상품의 개수가 줄었지만, 장바구니 개수보다는 많아요") {

        val basket = Basket("goseKose")
        basket.add(book, 100L)

        val afterBook = Product(
            id = 1L,
            name = "Book",
            price = 3000,
            stock = 149L,
        )

        `when`("장바구니에 있는 상품에 수량을 증가 업데이트하면") {
            val requiredQuantity = 150L

            then("재고 부족 예외가 발생해야 해요") {
                val exception = shouldThrow<BasketException> {
                    basket.updateQuantity(afterBook, requiredQuantity)
                }

                exception.cause.message shouldBe BasketErrorCode.SHORTAGE_STOCK.code
                exception.message shouldBe BasketErrorCode.SHORTAGE_STOCK.message
            }
        }
    }

    given("유저가 장바구니에 상품을 담았는데, 다른 유저가 상품 주문을 완료하여 상품의 개수가 줄어, 장바구니 개수보다 적어요") {

        val basket = Basket("goseKose")
        basket.add(book, 100L)

        val afterBook = Product(
            id = 1L,
            name = "Book",
            price = 3000,
            stock = 50L,
        )

        `when`("장바구니에 있는 상품에 수량을 증가 업데이트하면,") {
            val requiredQuantity = 51L

            then("재고 부족 예외가 발생해야 해요") {
                val exception = shouldThrow<BasketException> {
                    basket.updateQuantity(afterBook, requiredQuantity)
                }

                exception.cause.message shouldBe BasketErrorCode.SHORTAGE_STOCK.code
                exception.message shouldBe BasketErrorCode.SHORTAGE_STOCK.message
            }
        }

        `when`("장바구니에 있는 상품에 수량을 재고 수량보다 적게 바꾸면,") {
            val requiredQuantity = 49L
            basket.updateQuantity(afterBook, requiredQuantity)

            val basketItems = basket.getBasketItem()
            then("장바구니에 저장된 상품 수량이 줄어들고, 총 장바구니 금액이 감소해요") {
                basketItems.size shouldBe 1
                basketItems[0].quantity shouldBe requiredQuantity
                basket.totalPrice shouldBe afterBook.price * requiredQuantity
            }
        }
    }

    given("유저가 책을 이미 장바구니에 넣었어요") {
        val basket = Basket("goseKose")
        val firstRequiredQuantity = 100L
        basket.add(book, firstRequiredQuantity)

        `when`("수량 변경 없이 장바구니에 동일한 상품을 추가하면") {
            val secondRequiredQuantity = 900L
            basket.add(book, secondRequiredQuantity)

            val basketItems = basket.getBasketItem()
            then("상품의 수량이 합쳐져야 해요") {
                basketItems.size shouldBe 1
                basketItems[0].quantity shouldBe  firstRequiredQuantity + secondRequiredQuantity
                basket.totalPrice shouldBe book.price * (firstRequiredQuantity + secondRequiredQuantity)
            }
        }

        `when`("수량 변경 없이 동일한 상품을 추가할 때, 재고보다 많으면") {
            val secondRequiredQuantity = 901L

            then("재고 부족 예외가 발생해야 해요") {
                val exception = shouldThrow<BasketException> {
                    basket.add(book, secondRequiredQuantity)
                }

                exception.cause.message shouldBe BasketErrorCode.SHORTAGE_STOCK.code
                exception.message shouldBe BasketErrorCode.SHORTAGE_STOCK.message
            }
        }
    }

})