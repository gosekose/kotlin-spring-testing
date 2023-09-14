package com.order.adapter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class AdapterApplication

fun main(args: Array<String>) {
    runApplication<AdapterApplication>(*args)
}
