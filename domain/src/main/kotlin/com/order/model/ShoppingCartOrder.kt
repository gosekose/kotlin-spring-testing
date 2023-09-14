package com.order.model

data class ShoppingCartOrder(
    val product: Product,
    val amount: Long,
)