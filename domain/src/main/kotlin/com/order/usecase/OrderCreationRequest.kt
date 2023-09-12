package com.order.usecase

import com.order.model.Payment
import com.order.model.Product
import com.order.model.User

data class OrderCreationRequest(
    val user: User,
    val product: Product,
    val payment: Payment,
)