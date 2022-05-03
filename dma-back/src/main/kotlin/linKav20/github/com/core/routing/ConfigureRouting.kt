package linKav20.github.com.core.routing

import io.ktor.server.application.*
import linKav20.github.com.entities.results.analyze.api.configureRoutingAnalyze
import linKav20.github.com.entities.sender.configureRoutingSend
import linKav20.github.com.entities.test.api.configureRoutingTests
import linKav20.github.com.entities.user.api.configureRoutingUsers

fun Application.configureRouting(){
    configureBaseRouting()
    configureRoutingUsers()
    configureRoutingTests()
    configureRoutingAnalyze()
    configureRoutingSend()
}