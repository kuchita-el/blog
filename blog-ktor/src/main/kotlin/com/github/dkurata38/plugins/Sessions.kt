package com.github.dkurata38.plugins

import io.ktor.server.application.*
import io.ktor.server.sessions.*

fun Application.configureSessions() {
    install(Sessions) {
        cookie<UserSession>("user-session") {
            cookie.path = "/"
            cookie.maxAgeInSeconds = 60 * 60
        }
    }
}

data class UserSession(val username: String)
