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
import linKav20.github.com.entities.user.basicUser.models.UserModel

fun Application.configureRoutingAuth() {
    installAuth()
    addTestUsers()

    routing {
        post("/check") {
            var user: UserModel? = null

            try {
                user = call.receive<UserModel>()
            } catch (ex: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Incorrect user format")
            }

            if (user != null && findUser(user) != null) {
                call.respond(HttpStatusCode.OK, user)
            } else {
                call.respond(HttpStatusCode.Unauthorized, "User not here")
            }
        }

        post("/login") {
            var user: UserModel? = null

            try {
                user = call.receive<UserModel>()
            } catch (ex: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Incorrect user format")
            }

            if (user != null && findUser(user!!) != null) {
                val token = generateJWTToken(user!!)
                call.respond(hashMapOf("token" to token))
            } else {
                call.respond(HttpStatusCode.Unauthorized, "User not here")
            }
        }

        post("/registration") {
            var user: UserModel? = null

            try {
                user = call.receive<UserModel>()
            } catch (ex: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Incorrect user format")
            }

            if (user != null) {
                if (findUserByLogin(user!!) == null) {
                    addUser(user!!)
                } else {
                    call.respond(HttpStatusCode.BadRequest, "User with login ${user!!.login} is already exist")
                }
            }

            call.respond(HttpStatusCode.OK, "Users successfully registered!")
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