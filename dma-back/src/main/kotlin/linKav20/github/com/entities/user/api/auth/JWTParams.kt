package linKav20.github.com.entities.user.api.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import linKav20.github.com.entities.user.models.UserModel
import java.util.*

const val secret = "DMA-SYSTEM"
const val issuer = "dma-system"
const val audience = "dma-system-working"
const val myRealm = "access-to-dma-system"
const val alive = 300000
const val claimLogin = "username"
const val claimPassword = "password"

fun generateJWTToken(user: UserModel): String = JWT.create()
    .withAudience(audience)
    .withIssuer(issuer)
    .withClaim("username", user.username)
    .withExpiresAt(Date(System.currentTimeMillis() + 60000))
    .sign(Algorithm.HMAC256(secret))