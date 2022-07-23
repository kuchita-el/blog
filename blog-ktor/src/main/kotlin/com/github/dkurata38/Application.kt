package com.github.dkurata38

import com.github.dkurata38.plugins.configureRouting
import com.github.dkurata38.plugins.configureSecurity
import com.github.dkurata38.plugins.configureSessions
import com.github.dkurata38.plugins.configureTemplating
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureTemplating()
        configureSecurity()
        configureSessions()
    }.start(wait = true)
}
