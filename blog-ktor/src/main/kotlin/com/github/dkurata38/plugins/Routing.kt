package com.github.dkurata38.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.thymeleaf.*

fun Application.configureRouting() {
    routing {
        authenticate("auth-form") {
            post("/login") {
                val principal = call.principal<UserIdPrincipal>()
                if (principal == null) {
                    call.respond(HttpStatusCode.Unauthorized)
                } else {
                    call.sessions.set(UserSession(principal.name))
                    call.respondRedirect("/")
                }
            }
        }

        authenticate("auth-session") {
            get("/") {
                val name = call.sessions.get<UserSession>()!!.username
                call.respond(ThymeleafContent("index", mapOf("name" to name)))
            }

            post("/logout") {
                call.sessions.clear<UserSession>()
                call.respondRedirect("/login")
            }
        }

        get("/login") {
            call.respond(ThymeleafContent("login", mapOf()))
        }

        static("/static") {
            resources("static")
        }
    }
}
