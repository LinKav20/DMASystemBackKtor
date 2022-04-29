package linKav20.github.com.answer.api

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRoutingAnswers() {

    routing {
        get("/answers") {
            call.respondText("Hello there in answers!")
        }
    }
}
