package linKav20.github.com.core.routing

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureBaseRouting(){
    routing {
        get("/"){
            call.respondText("Hi there in DMA System!")
        }
    }
}