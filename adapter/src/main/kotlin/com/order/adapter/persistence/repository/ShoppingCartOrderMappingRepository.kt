package com.order.adapter.persistence.repository

import com.order.adapter.persistence.entity.ShoppingCartOrderMappingEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShoppingCartOrderMappingRepository : JpaRepository<ShoppingCartOrderMappingEntity, Long>