package linKav20.github.com.core

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import linKav20.github.com.core.databases.connectDataBase
import linKav20.github.com.core.routing.configureRouting
import linKav20.github.com.core.security.configureCors


fun Application.serverSettings(){
    connectDataBase()
    configureRouting()
    configureCors()

    install(ContentNegotiation) {
        json()
    }
}