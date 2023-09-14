package com.order.application.service

import com.order.application.request.HoldingStockRequest
import com.order.application.port.out.StockCommandPort
import org.springframework.stereotype.Service

@Service
class ProductStockService(
    val stockCommandPort: StockCommandPort,
) {
    fun holdProductsStockToBuy(holdingStockRequest: HoldingStockRequest) {
        stockCommandPort.holdStockToPay(holdingStockRequest)
    }
}