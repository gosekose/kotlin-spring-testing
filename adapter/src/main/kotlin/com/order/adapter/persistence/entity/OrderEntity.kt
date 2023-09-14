package com.order.adapter.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "order")
data class OrderEntity(

    @Column
    val txId: String,

    @Column
    val userId: Long,

    @Column
    val productId: Long,

    @Column
    val paymentId: Long,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var id: Long = 0L
) : BaseEntity()