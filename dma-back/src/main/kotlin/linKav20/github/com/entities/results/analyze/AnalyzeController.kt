package linKav20.github.com.entities.results.analyze

import linKav20.github.com.entities.category.categotiesHierarchy.getParentString
import linKav20.github.com.entities.category.tables.CategoryEntity
import linKav20.github.com.entities.results.analyze.models.AnalyzeModel
import linKav20.github.com.entities.results.analyze.tables.AnalyzeEntity
import linKav20.github.com.entities.results.analyze.tables.AnalyzeTable
import linKav20.github.com.entities.results.tables.ResultEntity
import linKav20.github.com.entities.results.tables.ResultsTable
import linKav20.github.com.entities.test.TestState
import linKav20.github.com.entities.test.findTestById
import linKav20.github.com.entities.test.getTest
import linKav20.github.com.entities.test.models.TestModel
import org.jetbrains.exposed.sql.select
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
    val analytics = transaction {
        AnalyzeEntity.wrapRows(query).toList()
    }
    return analytics.isNotEmpty()
}

fun getAnalyze(testModel: TestModel): List<AnalyzeModel> {
    val query = querySQLAnalyzeTest(testModel.id)
    val analytics = transaction {
        AnalyzeEntity.wrapRows(query).toList()
    }
    val result = mutableListOf<AnalyzeModel>()
    for (analytic in analytics) {
        result.add(toAnalyzeModel(analytic))
    }
    return result
}

fun createAnalyze(testModel: TestModel) {
    val query = transaction {
        ResultsTable
            .select {
                ResultsTable.test eq testModel.id
            }
    }
    val analytics = transaction {
        ResultEntity.wrapRows(query).toList()
    }

    val result = getAnalyticsByCategories(analytics)

    transaction {
        for (res in result) {
            AnalyzeEntity.new {
                test = findTestById(testModel.id)!!
                category = res.key
                stats = res.value.toInt()
            }
        }
    }
}

fun toAnalyzeModel(analyzeEntity: AnalyzeEntity): AnalyzeModel = transaction {
    AnalyzeModel(
        getParentString(analyzeEntity.category.categoryId.toLong()),
        analyzeEntity.category.name,
        analyzeEntity.stats.toInt()
    )
}

private fun querySQLAnalyzeTest(id: Long) = transaction {
    AnalyzeTable
        .select {
            AnalyzeTable.test eq id
        }
}

private fun getAnalyticsByCategories(analytics: List<ResultEntity>): Map<CategoryEntity, Double> {
    var result = mutableMapOf<CategoryEntity, Double>()
    transaction {
        for (analytic in analytics) {
            if (result.containsKey(analytic.category)) {
                val current = result[analytic.category]!!
                val newValue = (current + analytic.answer.toDouble()) / 2
                result[analytic.category] = newValue
            } else {
                result.put(analytic.category, analytic.answer.toDouble())
            }
        }
    }
    return result
}
