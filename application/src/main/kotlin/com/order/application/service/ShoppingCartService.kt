package com.order.application.service

import com.order.model.ShoppingCartOrder
import com.order.usecase.ShoppingCartUseCase
import org.springframework.stereotype.Service

@Service
class ShoppingCartService(
    private val shoppingCartUseCase: ShoppingCartUseCase,
) {

    fun addProduct(shoppingCartOrder: ShoppingCartOrder) {
        shoppingCartUseCase.addProduct(shoppingCartOrder)
    }

    fun removeProducts(shoppingCartOrder: List<ShoppingCartOrder>) {
        shoppingCartUseCase.removeProducts(shoppingCartOrder)
    }
}