package com.order.share

enum class ShoppingCartPaymentStatus(
    val description: String,
) {
    WAITING("결제 대기"),
    COMPLETED("결제 완료"),
    CANCELLATION("결제 취소"),
}
