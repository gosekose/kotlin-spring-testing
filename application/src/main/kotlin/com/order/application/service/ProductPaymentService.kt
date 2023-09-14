package com.order.application.service

import com.order.application.port.out.ProductPaymentCommandPort
import com.order.application.request.PaymentProceedingRequest
import com.order.application.request.PaymentReadyRequest
import com.order.application.response.PaymentMean
import com.order.application.response.PaymentMeansResponse
import com.order.model.ShoppingCartOrder
import com.order.model.User
import com.order.share.PaymentStatus
import com.order.usecase.ProductPaymentUseCase
import org.springframework.stereotype.Service

@Service
class ProductPaymentService(
    private val productPaymentUseCase: ProductPaymentUseCase,
    private val productPaymentCommandPort: ProductPaymentCommandPort,
) {
    fun getBuyingProductMeans(): PaymentMeansResponse {
        return PaymentMeansResponse(PaymentMean.getPaymentMeans())
    }

    fun readyPayment(user: User, shoppingCartOrder: List<ShoppingCartOrder>): Long {
        return productPaymentCommandPort.savePayment(
            PaymentReadyRequest(
                user = user,
                shoppingCartOrders = shoppingCartOrder,
                paymentStatus = PaymentStatus.READY,
            )
        )
    }

    fun payProduct(paymentProceedingRequest: PaymentProceedingRequest) {
        productPaymentCommandPort.proceedPayment()
    }
}