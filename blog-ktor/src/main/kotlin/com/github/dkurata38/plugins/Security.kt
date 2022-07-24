package com.github.dkurata38.plugins

import at.favre.lib.crypto.bcrypt.BCrypt
import io.ktor.server.application.*
import io.ktor.server.auth.*
import kotlin.random.Random

fun Application.configureSecurity() {
    install(Authentication) {
        form("auth-form") {
            userParamName = "username"
            passwordParamName = "password"
            val username = "user"
            val password = PasswordGenerator.generate()
            println("username: $username")
            println("password: $password")
            val encodedPassword = PasswordEncoder.encode(password)
            validate {
                if (it.name == username && PasswordEncoder.verify(it.password, encodedPassword)) {
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

object PasswordGenerator {
    private const val availableCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789/*-+.,!#%&()~|_"

    fun generate(length: Int = 8): String {
        return IntRange(1, length)
            .map { Random.nextInt(0, availableCharacters.length) }
            .map { availableCharacters[it] }
            .joinToString("")
    }
}

object PasswordEncoder {
    private val version = BCrypt.Version.VERSION_2B
    private val encoder = BCrypt.with(version)
    private val verifier = BCrypt.verifyer(version)

    fun encode(rawPassword: String): String {
        return encoder.hashToString(10, rawPassword.toCharArray())
    }

    fun verify(rawPassword: String, encodedPassword: String): Boolean {
        return verifier.verifyStrict(rawPassword.toCharArray(), encodedPassword.toCharArray())
            .let {
                println(it.formatErrorMessage)
                it.verified
            }
    }
}
