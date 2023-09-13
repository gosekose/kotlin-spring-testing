package com.example.kotlinspringtest.testdouble

import java.time.Month

class ChristmasDiscountPolicy : DiscountPolicy {

    companion object {
        const val CHRISTMAS_DAY = 25
        const val DEFAULT_RATE = 0.0
        const val DISCOUNT_RATE = 0.15
    }

    override fun apply(amount: Double): Double {
        val nowDay = ClockUtil.now()

        val rate = when {
            nowDay.month == Month.DECEMBER && nowDay.dayOfMonth == CHRISTMAS_DAY -> DISCOUNT_RATE
            else -> DEFAULT_RATE
        }

        return amount - (amount * rate)
    }
}