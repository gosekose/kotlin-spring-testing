package com.order.model

class Product(
    val name: String,
    val price: Long,
    val stock: Long,
    val seller: User,
    var id: Long = 0L,
) {

}