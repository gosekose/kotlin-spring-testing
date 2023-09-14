package com.order.exception

enum class ValidateExceptionCode(
    val code: String,
    val message: String,
) {
    FULL_SHOPPING_CART("400", "장바구니가 가득찼습니다.")
}
