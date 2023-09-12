package com.order.model

import com.order.share.PaymentMeans

class Payment(
    val price: Long,
    val means: PaymentMeans,
    var id: Long = 0L,
) {
}