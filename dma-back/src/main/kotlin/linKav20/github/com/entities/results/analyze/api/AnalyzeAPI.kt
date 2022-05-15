package linKav20.github.com.entities.results.analyze.api

import com.google.gson.GsonBuilder
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import linKav20.github.com.entities.results.analyze.getAnalyze
import linKav20.github.com.entities.results.analyze.models.AnalyzeModel


fun Application.configureRoutingAnalyze() {
    val basePath = "analyze"
    val gson = GsonBuilder().setPrettyPrinting().create()


    routing {
        authenticate("auth-jwt") {
            get("/$basePath") {
                if (call.request.queryParameters["id"] != null) {
                    val id = try {
                        call.request.queryParameters["id"]!!.toInt()
                    } catch (ex: Exception) {
                        call.respond(HttpStatusCode.BadRequest, "ID must be integer value")
                    }

                    if (id as Int <= 0) call.respond(HttpStatusCode.BadRequest, "ID must be greater than 0")
                    val analyze = try {
                        call.respond(HttpStatusCode.OK, gson.toJson(getAnalyze(id)))
                    } catch (ex: Exception) {
                        call.respond(HttpStatusCode.NotFound, "Cannot find analyze for test with ID $id")
                    }
                }

                call.respond(HttpStatusCode.OK)
            }
        }
    }
}
