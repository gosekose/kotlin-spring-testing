package com.order.model

import com.order.share.PaymentMeans

class ProductPayment(
    val shoppingProducts: List<ShoppingCartOrder>,
    val totalPrice: Long,
    val means: PaymentMeans,
)