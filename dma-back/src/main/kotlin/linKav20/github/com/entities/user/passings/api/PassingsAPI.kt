package linKav20.github.com.entities.user.passings.api

import com.google.gson.GsonBuilder
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import linKav20.github.com.entities.results.getCountAllPassedTest
import linKav20.github.com.entities.temp.getTest1
import linKav20.github.com.entities.test.findTestById
import linKav20.github.com.entities.test.tables.TestEntity
import linKav20.github.com.entities.user.passings.getPassings

fun Application.configureRoutePass(){
    val gson = GsonBuilder().setPrettyPrinting().create()

    routing {
        get("/pass"){
            if (call.request.queryParameters["id"] != null) {
                val testId = try {
                    call.request.queryParameters["id"]!!.toInt()
                } catch (ex: Exception) {
                    call.respond(HttpStatusCode.BadRequest, "ID must be integer value")
                }

                if (testId as Int <= 0) call.respond(HttpStatusCode.BadRequest, "ID must be greater than 0")
                val test = try {
                    findTestById(testId.toLong())
                } catch (ex: Exception) {
                    call.respond(HttpStatusCode.NotFound, "Cannot find test with ID $testId")
                }

                val passings = getPassings(test as TestEntity).count()
                val passed = getCountAllPassedTest(testId.toLong())
                call.respond(HttpStatusCode.OK, "$passed from $passings")
            }

            call.respond(HttpStatusCode.OK, gson.toJson(getTest1()))
        }
    }
}