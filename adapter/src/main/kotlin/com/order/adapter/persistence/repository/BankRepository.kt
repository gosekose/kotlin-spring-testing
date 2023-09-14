package com.order.adapter.persistence.repository

import com.order.adapter.persistence.entity.BankEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BankRepository : JpaRepository<BankEntity, Long>