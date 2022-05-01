package linKav20.github.com.entities.user.api

import com.google.gson.GsonBuilder
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import linKav20.github.com.entities.user.api.auth.configureRoutingAuth
import linKav20.github.com.entities.user.getUsers
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureRoutingUsers() {

    configureRoutingAuth()

    routing {
        get("/users") {
            call.respond(getUsers())
        }
    }
}