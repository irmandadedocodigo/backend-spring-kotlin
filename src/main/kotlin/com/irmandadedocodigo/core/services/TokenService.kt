package com.irmandadedocodigo.core.services

import com.irmandadedocodigo.core.infra.entities.User
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class TokenService {

    @Value("{api.security.token.secret}")
    lateinit var secret: String

    fun generate(user: User): String? {
        return try {
            JWT
                .create()
                .withIssuer("core-auth-api")
                .withSubject(user.id)
                .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-3")))
                .sign(Algorithm.HMAC256(secret))
        } catch (exception: JWTCreationException) {
            throw RuntimeException("Error while generating token", exception)
        }
    }

    fun validate(token: String): String? {
        return try {
            JWT
                .require(Algorithm.HMAC256(secret))
                .withIssuer("core-auth-api")
                .build()
                .verify(token)
                .subject
        } catch (exception: JWTVerificationException) {
            return ""
        }
    }
}