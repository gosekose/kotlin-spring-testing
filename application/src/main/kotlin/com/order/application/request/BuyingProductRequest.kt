package com.order.application.request

import com.order.model.ShoppingCartOrder
import com.order.model.User

data class BuyingProductRequest(
    val user: User,
    val shoppingCartOrders: List<ShoppingCartOrder>,
)