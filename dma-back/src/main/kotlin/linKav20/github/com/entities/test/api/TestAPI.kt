package linKav20.github.com.entities.test.api

import com.google.gson.GsonBuilder
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import linKav20.github.com.entities.test.models.TestModel
import linKav20.github.com.entities.temp.*
import linKav20.github.com.entities.test.saveTest

fun Application.configureRoutingTests() {
    routing {
        get("/tests") {
            call.respondText("tests")
        }

        post("/test/create") {
            val data = call.receive<String>();

            val gson = GsonBuilder().setPrettyPrinting().create()
            val test = gson.fromJson(data, TestModel::class.java)
            saveTest(test)

            call.respond(gson.toJson(test))
        }

        get("/test") {
            val test = getTest1()
            val gson = GsonBuilder().setPrettyPrinting().create()

            call.respond(gson.toJson(test))
        }
    }
}