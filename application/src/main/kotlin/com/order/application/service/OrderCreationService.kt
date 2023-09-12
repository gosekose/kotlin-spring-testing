package com.order.application.service

import com.order.application.port.out.OrderCommandPort
import com.order.usecase.OrderCreationRequest
import com.order.usecase.OrderCreationUseCase

class OrderCreationService(
    private val orderCommandPort: OrderCommandPort,
    private val orderCreationUseCase: OrderCreationUseCase,
) {

    fun creationOrder(orderCreationRequest: OrderCreationRequest): Long {
        val order = orderCreationUseCase.createOrder(orderCreationRequest)
        return orderCommandPort.saveOrder(order)
    }
}