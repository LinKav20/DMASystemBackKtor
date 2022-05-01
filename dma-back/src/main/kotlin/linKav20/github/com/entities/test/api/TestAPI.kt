package linKav20.github.com.entities.test.api

import com.google.gson.GsonBuilder
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import linKav20.github.com.entities.test.models.TestModel
import linKav20.github.com.entities.temp.*

fun Application.configureRoutingTests() {
    routing {
        get("/tests") {
            call.respondText("tests")
        }

        post("/test/create") {
            val data = call.receive<String>();
            val gson = GsonBuilder().setPrettyPrinting().create()

            val test = gson.fromJson(data, TestModel::class.java)
            call.respond(gson.toJson(test))
        }

        get("/test") {
            val test = getTest1()
            val gson = GsonBuilder().setPrettyPrinting().create()

            call.respond(gson.toJson(test))
        }
    }
}