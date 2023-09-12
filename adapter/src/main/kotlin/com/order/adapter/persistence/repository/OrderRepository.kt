package com.order.adapter.persistence.repository

import com.order.adapter.persistence.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<OrderEntity, Long>