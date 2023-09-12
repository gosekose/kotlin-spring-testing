package com.order.usecase

import com.order.model.Order
class OrderCreation : OrderCreationUseCase {
    override fun createOrder(orderCreationRequest: OrderCreationRequest): Order {
        return Order.of(
            orderCreationRequest.user,
            orderCreationRequest.product,
            orderCreationRequest.payment,
        )
    }

}