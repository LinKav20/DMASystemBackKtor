package linKav20.github.com.entities.test.api

import com.google.gson.GsonBuilder
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import linKav20.github.com.entities.test.models.TestModel
import linKav20.github.com.entities.temp.*
import linKav20.github.com.entities.test.getTest
import linKav20.github.com.entities.test.saveTest

fun Application.configureRoutingTests() {
    val basePath = "test"
    routing {
        post("/$basePath/create") {
            val data = call.receive<String>();
            val gson = GsonBuilder().setPrettyPrinting().create()
            val test = gson.fromJson(data, TestModel::class.java)
            saveTest(test)
            call.respond(gson.toJson(test))
        }

        get("/$basePath") {
            val gson = GsonBuilder().setPrettyPrinting().create()

            if (call.request.queryParameters["id"] != null) {
                val id = try {
                    call.request.queryParameters["id"]!!.toInt()
                } catch (ex: Exception) {
                    call.respond(HttpStatusCode.BadRequest)
                }

                val test = try {
                    getTest(id as Int)
                } catch (ex: Exception) {
                    call.respond(HttpStatusCode.NotFound)
                }

                call.respond(gson.toJson(test as TestModel))
            }

            val test = getTest1()
            call.respond(gson.toJson(test))
        }
    }
}