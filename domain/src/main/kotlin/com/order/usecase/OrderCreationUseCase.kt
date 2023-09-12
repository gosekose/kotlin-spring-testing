package com.order.usecase

import com.order.model.Order

interface OrderCreationUseCase {

    fun createOrder(
        orderCreationRequest: OrderCreationRequest,
    ): Order
}