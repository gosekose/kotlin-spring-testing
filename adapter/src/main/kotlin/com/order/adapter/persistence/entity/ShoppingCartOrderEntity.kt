package com.order.adapter.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "shopping_cart_order")
data class ShoppingCartOrderEntity(

    @Column
    val productId: Long,

    @Column
    val amount: Long,

    @Column
    val userId: Long,

    @Id
    @Column
    @GeneratedValue
    val id: Long = 0L,
) : BaseEntity()
