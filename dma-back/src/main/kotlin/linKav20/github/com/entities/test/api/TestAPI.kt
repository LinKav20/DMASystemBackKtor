package linKav20.github.com.entities.test.api

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRoutingTests() {
    routing {
        get("/tests"){
            call.respondText("tests")
        }
    }
}