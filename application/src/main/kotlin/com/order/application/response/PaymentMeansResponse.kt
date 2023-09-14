package com.order.application.response

import com.order.share.PaymentMeans

data class PaymentMeansResponse(
    val paymentMeans: List<PaymentMean>
)

data class PaymentMean(
    val mean: String,
    val description: String,
) {
    companion object {
        fun getPaymentMeans(): List<PaymentMean> {
            return PaymentMeans.values().map { PaymentMean(it.name, it.description) }
        }
    }
}
