package com.order.application.port.out

import com.order.application.request.PaymentReadyRequest

interface ProductPaymentCommandPort {

    fun savePayment(paymentReadyRequest: PaymentReadyRequest): Long

    fun proceedPayment(): Long
}