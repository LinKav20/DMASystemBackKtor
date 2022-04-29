package linKav20.github.com

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import linKav20.github.com.answer.api.configureRoutingAnswers

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRoutingAnswers()
    }.start(wait = true)
}
