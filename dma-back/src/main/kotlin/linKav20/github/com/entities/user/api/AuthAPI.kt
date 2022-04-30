package linKav20.github.com.entities.user.api

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRoutingAuth() {
    routing {
        get("/auth") {
            call.respondText("auth")
        }
    }
}