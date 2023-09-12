package com.order.usecase

import com.order.model.Order
import org.springframework.stereotype.Service

@Service
class OrderCreation : OrderCreationUseCase {
    override fun createOrder(orderCreationRequest: OrderCreationRequest): Order {
        return Order.of(
            orderCreationRequest.user,
            orderCreationRequest.product,
            orderCreationRequest.payment,
        )
    }

}