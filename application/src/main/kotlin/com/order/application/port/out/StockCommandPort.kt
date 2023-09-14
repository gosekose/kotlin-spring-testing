package com.order.application.port.out

import com.order.application.request.HoldingStockRequest
import org.springframework.stereotype.Service

@Service
interface StockCommandPort {

    fun holdStockToPay(holdingStockRequest: HoldingStockRequest)
    fun minusStock()
}