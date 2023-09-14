package com.order.adapter.persistence.entity

import com.order.share.PaymentMeans
import com.order.share.PaymentStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "payment")
data class PaymentEntity(

    @Column
    val price: Long,

    @Column
    val bankAccountId: Long,

    @Column
    @Enumerated(EnumType.STRING)
    val means: PaymentMeans,

    @Column
    @Enumerated
    val paymentStatus: PaymentStatus,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Long = 0L,
) : BaseEntity()