package com.order.application.port.out

import com.order.model.Order

interface OrderCommandPort {
    fun saveOrder(order: Order): Long
}