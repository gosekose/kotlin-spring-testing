package com.order.share

import org.springframework.stereotype.Component
import java.util.UUID

@Component
object UidCreation {

    fun creationUid(): String {
        return UUID.randomUUID().toString()
    }
}