package com.order.adapter.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "shopping_cart_order_mapping")
data class ShoppingCartOrderMappingEntity(

    @Column
    val shoppingCartOrderId: Long,

    @Column
    val shoppingCartId: Long,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Long = 0L
) : BaseEntity()
