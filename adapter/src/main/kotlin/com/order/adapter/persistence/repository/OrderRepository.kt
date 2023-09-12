package com.order.adapter.persistence.repository

import com.order.adapter.persistence.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<OrderEntity, Long>