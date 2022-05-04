package linKav20.github.com

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import linKav20.github.com.core.databases.connectDataBase
import linKav20.github.com.core.routing.configureRouting
import linKav20.github.com.core.serverSettings

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        serverSettings()
    }.start(wait = true)
}
