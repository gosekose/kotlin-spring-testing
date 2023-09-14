package com.order.adapter.persistence.entity

import com.order.model.BankCode
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "bank")
data class BankEntity(

    @Column
    @Enumerated(EnumType.STRING)
    val bankCode: BankCode,

    @Column
    val bankAccountNumber: String,

    @Column
    val userId: Long,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Long,
) : BaseEntity()
