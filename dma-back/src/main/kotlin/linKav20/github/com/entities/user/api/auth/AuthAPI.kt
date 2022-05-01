package linKav20.github.com.entities.user.api.auth

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import linKav20.github.com.entities.temp.addTestUsers
import linKav20.github.com.entities.user.*
import linKav20.github.com.entities.user.models.UserModel

fun Application.configureRoutingAuth() {
    installAuth()
    addTestUsers()

    routing {
        post("/check") {
            val user = call.receive<UserModel>()

            if (findUser(user)) {
                call.respond(HttpStatusCode.OK, user)
            } else {
                call.respond(HttpStatusCode.Unauthorized, "User not here")
            }
        }

        post("/login") {
            val user = call.receive<UserModel>()

            if (findUser(user)) {
                val token = generateJWTToken(user)
                call.respond(hashMapOf("token" to token))
            } else {
                call.respond(HttpStatusCode.Unauthorized, "User not here")
            }
        }

        post("/registration"){
            val user = call.receive<UserModel>()
            addUser(user)
            call.respond(HttpStatusCode.OK)
        }

        authenticate("auth-jwt") {
            get("/hello") {
                val principal = call.principal<JWTPrincipal>()
                val username = principal!!.payload.getClaim("username").asString()
                val expiresAt = principal.expiresAt?.time?.minus(System.currentTimeMillis())
                call.respondText("Hello, $username! Token is expired at $expiresAt ms.")
            }
        }
    }
}