package com.order.usecase

import com.order.model.ShoppingCartOrder
import org.springframework.stereotype.Service

@Service
interface ShoppingCartUseCase {
    fun addProduct(shoppingCartOrder: ShoppingCartOrder)

    fun removeProducts(shoppingCartToRemove: List<ShoppingCartOrder>)
}