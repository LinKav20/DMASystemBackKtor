package linKav20.github.com.entities.user.api.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import linKav20.github.com.entities.user.basicUser.models.UserModel
import java.util.*

const val secret = "DMA-SYSTEM"
const val issuer = "dma-system"
const val audience = "dma-system-working"
const val myRealm = "access-to-dma-system"
const val alive = 3000000

fun generateJWTToken(user: UserModel): String = JWT.create()
    .withAudience(audience)
    .withIssuer(issuer)
    .withClaim("username", user.login)
    .withExpiresAt(Date(System.currentTimeMillis() + alive))
    .sign(Algorithm.HMAC256(secret))