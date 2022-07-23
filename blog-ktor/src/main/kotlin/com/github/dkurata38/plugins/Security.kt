package com.github.dkurata38.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*

fun Application.configureSecurity() {
    install(Authentication) {
        form("auth-form") {
            userParamName = "username"
            passwordParamName = "password"
            validate {
                println(it)
                if (it.name == "dkurata38" && it.password == "dkurata38") {
                    UserIdPrincipal(it.name)
                } else {
                    null
                }
            }
        }

        session<UserSession>("auth-session") {
            validate {
                UserIdPrincipal(it.username)
            }
            challenge("/login")
        }
    }
}
