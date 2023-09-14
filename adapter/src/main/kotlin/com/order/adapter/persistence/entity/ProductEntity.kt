package com.order.adapter.persistence.entity

import com.order.model.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "product")
data class ProductEntity(

    @Column
    val name: String,

    @Column
    val price: Long,

    @Column
    val stock: Long,

    @Column
    val sellerId: Long,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Long = 0L,
) : BaseEntity()