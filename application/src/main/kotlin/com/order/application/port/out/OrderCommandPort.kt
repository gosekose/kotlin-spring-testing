package com.order.application.port.out

import com.order.model.Order
import org.springframework.stereotype.Service

@Service
interface OrderCommandPort {
    fun saveOrder(order: Order): Long
}