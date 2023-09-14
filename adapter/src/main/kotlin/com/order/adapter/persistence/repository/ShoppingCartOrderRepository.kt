package com.order.adapter.persistence.repository

import com.order.model.ShoppingCartOrder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShoppingCartOrderRepository : JpaRepository<ShoppingCartOrder, Long>