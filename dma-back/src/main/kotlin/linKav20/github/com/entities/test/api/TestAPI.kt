package linKav20.github.com.entities.test.api

import com.google.gson.GsonBuilder
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import linKav20.github.com.entities.answer.models.AnswerModel
import linKav20.github.com.entities.question.models.QuestionModel
import linKav20.github.com.entities.results.models.PassingChoice
import linKav20.github.com.entities.results.models.ResultModel
import linKav20.github.com.entities.test.models.TestModel
import linKav20.github.com.entities.temp.*
import linKav20.github.com.entities.test.getTest
import linKav20.github.com.entities.test.getTestByCreator
import linKav20.github.com.entities.test.saveTest
import linKav20.github.com.entities.user.findUserByLogin
import linKav20.github.com.entities.user.models.UserModel
import linKav20.github.com.entities.user.toUserModel

fun Application.configureRoutingTests() {
    val basePath = "test"
    val gson = GsonBuilder().setPrettyPrinting().create()

    routing {
        post("/$basePath/create") {
            val data = call.receive<String>();

            var test: TestModel? = null
            try {
                test = gson.fromJson(data, TestModel::class.java)
            } catch (ex: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Incorrect test format")
            }

            if (test == null) {
                call.respond(HttpStatusCode.BadRequest, "Test is null")
            } else {
                saveTest(test)
                call.respond(HttpStatusCode.OK, "Test saved successfully!")
            }
        }

        get("/$basePath") {
            if (call.request.queryParameters["id"] != null) {
                val id = try {
                    call.request.queryParameters["id"]!!.toInt()
                } catch (ex: Exception) {
                    call.respond(HttpStatusCode.BadRequest, "ID must be integer value")
                }

                val test = try {
                    getTest(id as Int)
                } catch (ex: Exception) {
                    call.respond(HttpStatusCode.NotFound, "Cannot find test with ID $id")
                }

                call.respond(HttpStatusCode.OK, gson.toJson(test as TestModel))
            }

            call.respond(HttpStatusCode.OK, gson.toJson(getTest1()))
        }

        authenticate("auth-jwt") {
            get("/${basePath}s") {
                val principal = call.principal<JWTPrincipal>()
                val username = principal!!.payload.getClaim("username").asString()

                val user = findUserByLogin(UserModel(username, "lol"))!!
                var tests = getTestByCreator(user)
                call.respond(HttpStatusCode.OK, gson.toJson(tests))
            }
        }

        get("/try") {
            val res = ResultModel("me@mail.me", listOf(PassingChoice(1, 2), PassingChoice(2, 2), PassingChoice(3, 5)))
            call.respond(gson.toJson(res))
        }

        get("/$basePath/pass") {
            val data = call.receive<String>();

            var result: ResultModel? = null
            try {
                result = gson.fromJson(data, ResultModel::class.java)
            } catch (ex: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Incorrect test format")
            }

            if (result == null) {
                call.respond(HttpStatusCode.BadRequest, "Test is null")
            } else {
                saveTest(getTest1())
                call.respond(HttpStatusCode.OK, "Test saved successfully!")
            }
        }
    }
}