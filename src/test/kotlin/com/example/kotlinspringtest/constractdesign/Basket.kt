package com.example.kotlinspringtest.constractdesign

class Basket(
    val username: String,
) {
    private val basket = mutableMapOf<Product, Long>()
    var totalPrice = 0L
        private set

    fun add(product: Product, quantity: Long) {
        validateQuantity(quantity)
        basket[product] = basket.getOrDefault(product, 0) + quantity
        plusTotalPrice(product.price, quantity)
    }

    fun remove(product: Product) {
        val quantityInBasket = basket[product]
        basket.remove(product)
        quantityInBasket?.let {
            minusTotalPrice(
                price = product.price,
                quantity = it,
            )
        }
    }

    fun decreaseQuantity(product: Product, requiredQuantity: Long) {
        validateQuantity(requiredQuantity)

        val quantityInBasket = basket[product]

        if (quantityInBasket != null) {
            changeOrRemoveQuantity(product, quantityInBasket, requiredQuantity)
        }
    }

    private fun changeOrRemoveQuantity(product: Product, quantityInBasket: Long, requiredQuantity: Long) {
        when {
            quantityInBasket - requiredQuantity <= 0 -> {
                basket.remove(product)
                minusTotalPrice(
                    price = product.price,
                    quantity = quantityInBasket,
                )
            }

            else -> {
                basket[product] = requiredQuantity
                minusTotalPrice(
                    price = product.price,
                    quantity = (quantityInBasket - requiredQuantity),
                )
            }
        }
    }

    private fun plusTotalPrice(price: Long, quantity: Long) {
        validateTotalPrice(totalPrice + price * quantity)
        totalPrice += price * quantity

    }

    private fun minusTotalPrice(price: Long, quantity: Long) {
        validateTotalPrice(totalPrice - price * quantity)
        totalPrice -= price * quantity
    }

    private fun validateQuantity(quantity: Long) {
        require(quantity >= 0) { throw BasketException(BasketErrorCode.INPUT_QUANTITY_NOT_NEGATIVE) }
    }

    private fun validateTotalPrice(expectedTotalPrice: Long) {
        require(expectedTotalPrice >= 0) { throw BasketException(BasketErrorCode.TOTAL_AMOUNT_NEGATIVE) }
    }
}

class Product(
    val id: Long,
    val name: String,
    val price: Long,
    val stock: Long,
)

enum class BasketErrorCode(
    val code: String,
    val message: String,
) {
    INPUT_QUANTITY_NOT_NEGATIVE("404", "요청 수량음 음수가 될 수 없습니다."),
    TOTAL_AMOUNT_NEGATIVE("500", "장바구니 금액은 0이 될 수 없습니다.")
}

class BasketException(
    val errorCode: BasketErrorCode,
) : RuntimeException() {
    override val cause: Throwable
        get() = Throwable(errorCode.code)
    override val message: String
        get() = errorCode.message

}