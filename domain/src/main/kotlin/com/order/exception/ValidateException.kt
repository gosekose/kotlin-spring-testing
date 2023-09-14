package com.order.exception

class ValidateException(
    private val code: ValidateExceptionCode,
) : RuntimeException() {
    override val cause: Throwable
        get() = Throwable(code.code)
    override val message: String
        get() = code.message
}