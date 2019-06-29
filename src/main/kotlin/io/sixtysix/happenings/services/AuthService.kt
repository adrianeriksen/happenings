package io.sixtysix.happenings.services

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.sixtysix.happenings.models.UserCredentials
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class AuthService(private val issuer: String, secret: String) {

    private val algorithm = Algorithm.HMAC256(secret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()

    fun generateToken(credentials: UserCredentials): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withClaim("id", credentials.id)
        .withClaim("email", credentials.email)
        .withExpiresAt(getExpiration())
        .sign(algorithm)

    private fun getExpiration(): Date {
        val expiresAt = LocalDateTime.now().plusHours(1)
        return Date.from(expiresAt.atZone(ZoneId.systemDefault()).toInstant())
    }
}