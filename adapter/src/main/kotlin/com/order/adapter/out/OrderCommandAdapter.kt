package com.order.adapter.out

import com.order.adapter.persistence.OrderEntity
import com.order.adapter.persistence.repository.OrderRepository
import com.order.application.port.out.OrderCommandPort
import com.order.model.Order

class OrderCommandAdapter(
    private val orderRepository: OrderRepository,
) : OrderCommandPort {
    override fun saveOrder(order: Order): Long {
        val orderEntity = OrderEntity(
            txId = order.txId,
            userId = order.user.id,
            productId = order.product.id,
            paymentId = order.payment.id,
        )
        return orderRepository.save(orderEntity).id
    }

}