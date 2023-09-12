package com.order.usecase

import com.order.model.Order
import org.springframework.stereotype.Service

@Service
interface OrderCreationUseCase {

    fun createOrder(
        orderCreationRequest: OrderCreationRequest,
    ): Order
}