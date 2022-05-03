package linKav20.github.com.entities.test.api

import com.google.gson.GsonBuilder
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import linKav20.github.com.entities.results.models.PassingChoice
import linKav20.github.com.entities.results.models.ResultModel
import linKav20.github.com.entities.results.saveResults
import linKav20.github.com.entities.temp.getTest1
import linKav20.github.com.entities.test.*
import linKav20.github.com.entities.test.models.TestModel
import linKav20.github.com.entities.user.findUserByLogin
import linKav20.github.com.entities.user.models.UserModel
import linKav20.github.com.entities.user.basicUser.tables.UserEntity

fun Application.configureRoutingTests() {
    val basePath = "test"
    val gson = GsonBuilder().setPrettyPrinting().create()

    routing {
        post("/$basePath/create") {
            val data = call.receive<String>()

            var test: TestModel? = null
            try {
                test = gson.fromJson(data, TestModel::class.java)
            } catch (ex: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Incorrect test format")
            }

            if (test == null) {
                call.respond(HttpStatusCode.BadRequest, "Test is null")
            } else {
                try {
                    saveTest(test)
                    call.respond(HttpStatusCode.OK, "Test saved successfully!")
                } catch (ex: Exception) {
                    call.respond(HttpStatusCode.BadRequest, "Cannot save test: ${ex.message}")
                }
            }
        }

        get("/$basePath") {
            if (call.request.queryParameters["id"] != null) {
                val id = try {
                    call.request.queryParameters["id"]!!.toInt()
                } catch (ex: Exception) {
                    call.respond(HttpStatusCode.BadRequest, "ID must be integer value")
                }

                if (id as Int <= 0) call.respond(HttpStatusCode.BadRequest, "ID must be greater than 0")
                val test = try {
                    getTest(id)
                } catch (ex: Exception) {
                    call.respond(HttpStatusCode.NotFound, "Cannot find test with ID $id")
                }

                call.respond(HttpStatusCode.OK, gson.toJson(test as TestModel))
            }

            call.respond(HttpStatusCode.OK, gson.toJson(getTest1()))
        }

        put("/$basePath"){
            if (call.request.queryParameters["id"] != null) {
                val id = try {
                    call.request.queryParameters["id"]!!.toInt()
                } catch (ex: Exception) {
                    call.respond(HttpStatusCode.BadRequest, "ID must be integer value")
                }

                val data = call.receive<String>()

                var newTest: TestModel? = null
                try {
                    newTest = gson.fromJson(data, TestModel::class.java)
                } catch (ex: Exception) {
                    call.respond(HttpStatusCode.BadRequest, "Incorrect test format")
                }

                if (newTest == null) {
                    call.respond(HttpStatusCode.BadRequest, "Test is null")
                }

                if (id as Int <= 0) call.respond(HttpStatusCode.BadRequest, "ID must be greater than 0")
                val test = try {
                    updateTest(id, newTest!!)
                } catch (ex: Exception) {
                    call.respond(HttpStatusCode.NotFound, "Cannot find test with ID $id")
                }

                call.respond(HttpStatusCode.OK, gson.toJson(getTest(id as Int)))
            }

            call.respond(HttpStatusCode.OK, "Nothing to update")
        }

        authenticate("auth-jwt") {
            get("/${basePath}s") {
                val principal = call.principal<JWTPrincipal>()
                val username = principal!!.payload.getClaim("username").asString()

                val user = findUserByLogin(UserModel(username, "lol")) ?: call.respond(
                    HttpStatusCode.BadRequest,
                    "Cannot find user with login $username in system"
                )
                val tests = getTestsByCreator(user as UserEntity).plus(getTestsByResponsible(user)).plus(getTestsByRedactor(user))
                call.respond(HttpStatusCode.OK, gson.toJson(tests))
            }
        }

        get("/try") {
            val res = ResultModel(
                "me@mail.me",
                listOf(
                    PassingChoice(1, 2),
                    PassingChoice(2, 2),
                    PassingChoice(3, 5)
                )
            )
            call.respond(gson.toJson(res))
        }

        post("/$basePath/pass") {
            val data = call.receive<String>()

            var result: ResultModel? = null
            try {
                result = gson.fromJson(data, ResultModel::class.java)
            } catch (ex: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Incorrect result format")
            }

            if (result == null) {
                call.respond(HttpStatusCode.BadRequest, "Result is null")
            } else {
                try {
                    saveResults(result)
                    call.respond(HttpStatusCode.OK, "Result saved successfully!")
                } catch (ex: Exception) {
                    call.respond(HttpStatusCode.BadRequest, "Cannot save results: ${ex.message}")
                }
            }
        }
    }
}