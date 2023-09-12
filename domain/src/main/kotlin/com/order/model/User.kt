package com.order.model

class User(
    val username: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
    var id: Long = 0L,
) {

}