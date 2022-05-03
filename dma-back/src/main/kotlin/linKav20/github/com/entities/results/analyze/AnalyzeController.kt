package linKav20.github.com.entities.results.analyze

import linKav20.github.com.entities.results.analyze.models.AnalyzeModel
import linKav20.github.com.entities.test.TestState
import linKav20.github.com.entities.test.getTest
import linKav20.github.com.entities.test.models.TestModel
import org.jetbrains.exposed.sql.transactions.transaction

fun getAnalyze(id: Int): List<AnalyzeModel> {
    val test = getTest(id)

    if (test.testState != TestState.CLOSED) {
        throw Exception("Cannot analyze not closed tests")
    }

    if (!isAnalyzed(test)) {
        createAnalyze(test)
    }

    val analyze = getAnalyze(test)
    return analyze
}

fun isAnalyzed(testModel: TestModel): Boolean {
    val query = querySQLAnalyzeTest(testModel.id)
    return false
}

fun getAnalyze(testModel: TestModel): List<AnalyzeModel> {
    return listOf(AnalyzeModel("lol", "lol", 90L))
}

fun createAnalyze(testModel: TestModel) {

}

private fun querySQLAnalyzeTest(id:Long)= transaction {

}