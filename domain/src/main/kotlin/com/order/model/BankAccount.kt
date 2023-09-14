package com.order.model

data class BankAccount(
    val user: User,
    val bankCode: BankCode,
    val bankAccountNumber: String,
    val id: String? = "",
)