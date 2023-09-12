package com.order.model

import com.order.share.PaymentMeans

class Payment(
    val id: Long,
    val price: Long,
    val means: PaymentMeans,
) {
}