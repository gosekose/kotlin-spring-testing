package com.order.application.request

import com.order.model.Payment
import com.order.model.User

data class PaymentProceedingRequest(
    val user: User,
    val payment: Payment,
)
