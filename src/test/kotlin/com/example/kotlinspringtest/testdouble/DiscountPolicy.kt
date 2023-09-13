package com.example.kotlinspringtest.testdouble

interface DiscountPolicy {
    fun apply(amount: Double): Double
}