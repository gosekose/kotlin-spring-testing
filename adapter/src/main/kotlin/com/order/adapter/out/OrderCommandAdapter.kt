package com.order.adapter.out

import com.order.adapter.persistence.entity.OrderEntity
import com.order.adapter.persistence.repository.OrderRepository
import com.order.application.port.out.OrderCommandPort
import com.order.model.Order
import org.springframework.stereotype.Component

@Component
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