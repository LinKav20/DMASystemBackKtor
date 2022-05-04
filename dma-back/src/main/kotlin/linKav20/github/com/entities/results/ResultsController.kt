package linKav20.github.com.entities.results

import linKav20.github.com.entities.answer.findAnswerById
import linKav20.github.com.entities.answer.tables.AnswersTable
import linKav20.github.com.entities.answer.toAnswerModel
import linKav20.github.com.entities.category.findCategoryById
import linKav20.github.com.entities.category.tables.CategoriesTable
import linKav20.github.com.entities.question.findQuestionById
import linKav20.github.com.entities.question.tables.QuestionsTable
import linKav20.github.com.entities.question.toQuestionModel
import linKav20.github.com.entities.results.models.PassingChoice
import linKav20.github.com.entities.results.models.ResultModel
import linKav20.github.com.entities.results.tables.ResultEntity
import linKav20.github.com.entities.results.tables.ResultsTable
import linKav20.github.com.entities.test.findTestById
import linKav20.github.com.entities.test.getTest
import linKav20.github.com.entities.test.tables.TestsTable
import linKav20.github.com.entities.user.basicUser.tables.UsersTable
import linKav20.github.com.entities.user.passings.findPassingByMail
import linKav20.github.com.entities.user.passings.tables.PassingEntity
import linKav20.github.com.entities.user.passings.toPassingsToString
import linKav20.github.com.entities.user.redactors.tables.RedactorsTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

fun saveResults(resultModel: ResultModel) {
    transaction {
        val passing = findPassingByMail(resultModel.mail)
            ?: throw NullPointerException("Cannot find passing user with mail ${resultModel.mail} in system")
        for (choice in resultModel.results) {
            saveResult(passing, choice)
        }
    }
}

fun saveResult(passingEntity: PassingEntity, passingChoice: PassingChoice) {
    val questionEntity = findQuestionById(passingChoice.questionID)
        ?: throw NullPointerException("Cannot find question with ID ${passingChoice.questionID} in system")
    val answerEntity = findAnswerById(passingChoice.answerID)
        ?: throw NullPointerException("Cannot find answer with ID ${passingChoice.answerID} in system")
    val categoryEntity = findCategoryById(questionEntity.category.categoryId.toLong())
        ?: throw NullPointerException("Cannot find category with ID ${questionEntity.category.categoryId.toLong()} in system")
    val testEntity = findTestById(categoryEntity.test.testId.toLong())
        ?: throw NullPointerException("Cannot find test with ID ${questionEntity.category.categoryId.toLong()} in system")

    transaction {
        ResultEntity.new {
            test = testEntity
            category = categoryEntity
            passing = passingEntity
            question = questionEntity
            answer = answerEntity.weight
        }
    }
}

fun getCountAllPassedTest(id: Long) = run { getAllPassedTest(id).count() }

fun getAllPassedTest(id: Long): List<String> {
    val query = querySQLAllPassedTest(id)
    val passed = transaction {
        ResultEntity.wrapRows(query).toList()
    }
    val result = mutableListOf<String>()
    transaction {
        for (pass in passed) {
            result.add(pass.passing.mail)
        }
    }
    return result.distinct()
}

private fun querySQLAllPassedTest(id: Long) = transaction {
    ResultsTable.select {
        ResultsTable.test eq id
    }.withDistinct()
}
