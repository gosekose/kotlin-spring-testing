package com.order.application.request

import com.order.model.ShoppingCartOrder
import com.order.model.User
import com.order.share.PaymentStatus

data class PaymentReadyRequest(
    val user: User,
    val shoppingCartOrders: List<ShoppingCartOrder>,
    val paymentStatus: PaymentStatus,
)