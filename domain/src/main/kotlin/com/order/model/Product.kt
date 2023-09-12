package com.order.model

class Product(
    val id: Long,
    val name: String,
    val price: Long,
    val stock: Long,
    val seller: User,
) {

}