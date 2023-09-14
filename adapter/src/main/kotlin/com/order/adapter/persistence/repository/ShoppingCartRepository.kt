package com.order.adapter.persistence.repository

import com.order.adapter.persistence.entity.ShoppingCartEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShoppingCartRepository : JpaRepository<ShoppingCartEntity, Long>