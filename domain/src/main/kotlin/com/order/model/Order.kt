package com.order.model

import com.order.share.UidCreation

class Order(
    var id: Long = 0L,
    val txId: String,
    val user: User,
    val product: Product,
    val payment: Payment,
) {
    companion object {
        fun of(
            user: User,
            product: Product,
            payment: Payment,
        ): Order {
            return Order(
                txId = UidCreation.creationUid(),
                user = user,
                product = product,
                payment = payment,
            )
        }
    }
}