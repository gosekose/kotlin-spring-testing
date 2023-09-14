package com.order.model

import com.order.share.PaymentMeans
import com.order.share.PaymentStatus

data class Payment(
    val price: Long,
    val means: PaymentMeans,
    val bankAccount: BankAccount,
    val paymentStatus: PaymentStatus,
    var id: Long = 0L,
)