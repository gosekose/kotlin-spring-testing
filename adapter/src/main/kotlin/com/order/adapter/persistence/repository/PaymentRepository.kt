package com.order.adapter.persistence.repository

import com.order.adapter.persistence.entity.PaymentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentRepository : JpaRepository<PaymentEntity, Long>