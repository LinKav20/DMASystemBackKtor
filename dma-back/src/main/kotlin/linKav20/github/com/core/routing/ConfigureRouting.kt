package linKav20.github.com.core.routing

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import linKav20.github.com.entities.answer.api.configureRoutingAnswers
import linKav20.github.com.entities.user.api.configureRoutingUsers

fun Application.configureRouting(){
    configureRoutingUsers()
    configureRoutingAnswers()
}