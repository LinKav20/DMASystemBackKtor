package linKav20.github.com.core

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import linKav20.github.com.core.databases.connectDataBase
import linKav20.github.com.core.routing.configureRouting


fun Application.serverSettings(){
    connectDataBase()
    configureRouting()

    install(ContentNegotiation) {
        json()
    }
}