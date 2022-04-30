package linKav20.github.com.entities.user.api

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRoutingUsers() {

    configureRoutingAuth()

    routing {
        get("/users"){
            call.respondText("users")
        }
    }
}