package linKav20.github.com

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import linKav20.github.com.core.databases.connectDataBase
import linKav20.github.com.core.routing.configureRouting
import linKav20.github.com.entities.answer.api.configureRoutingAnswers
import linKav20.github.com.entities.user.api.configureRoutingUsers

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        connectDataBase()
        configureRouting()
    }.start(wait = true)
}