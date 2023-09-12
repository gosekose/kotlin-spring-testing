package com.order.share

import java.util.UUID

object UidCreation {

    fun creationUid(): String {
        return UUID.randomUUID().toString()
    }
}