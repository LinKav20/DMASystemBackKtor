package linKav20.github.com.entities.sender

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import linKav20.github.com.entities.test.findTestById
import linKav20.github.com.entities.user.passings.getPassings

fun Application.configureRoutingSend() {
    routing {
        authenticate("auth-jwt") {
            get("/send") {
                if (call.request.queryParameters["id"] != null) {
                    val id = try {
                        call.request.queryParameters["id"]!!.toInt()
                    } catch (ex: Exception) {
                        call.respond(HttpStatusCode.BadRequest, "ID must be integer value")
                    }

                    if (id as Int <= 0) call.respond(HttpStatusCode.BadRequest, "ID must be greater than 0")
                    val passings = try {
                        val test = findTestById(id.toLong())
                        getPassings(test!!)
                    } catch (ex: Exception) {
                        call.respond(HttpStatusCode.BadRequest, "Cannot send mails")
                    }

                    sendMessage(passings as List<String>)
                    call.respond(HttpStatusCode.OK, "All emails were sent")
                }
            }
        }
    }
}