package com.order.model

import com.order.exception.ValidateException
import com.order.exception.ValidateExceptionCode
import org.springframework.stereotype.Service

@Service
class ShoppingCart(
    private val shoppingProducts: MutableList<ShoppingCartOrder>,
) {
    fun addProduct(shoppingProduct: ShoppingCartOrder) {
        validateAddingProduct()
        shoppingProducts.add(shoppingProduct)
    }

    fun removeProducts(shoppingProductsToRemove: List<ShoppingCartOrder>) {
        shoppingProducts.minus(shoppingProductsToRemove.toSet())
    }

    fun getTotalPrice(): Long {
        return shoppingProducts.sumOf { it.product.price * it.amount }
    }

    private fun validateAddingProduct() {
        require(shoppingProducts.size <= MAX_SIZE) { throw ValidateException(ValidateExceptionCode.FULL_SHOPPING_CART) }
    }

    companion object {
        const val MAX_SIZE = 1000
    }
}