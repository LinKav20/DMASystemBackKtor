package linKav20.github.com

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import linKav20.github.com.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSecurity()
        configureHTTP()
    }.start(wait = true)
}
