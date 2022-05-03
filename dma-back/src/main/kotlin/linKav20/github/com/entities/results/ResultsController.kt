package linKav20.github.com.entities.results

import linKav20.github.com.entities.answer.findAnswerById
import linKav20.github.com.entities.answer.toAnswerModel
import linKav20.github.com.entities.question.findQuestionById
import linKav20.github.com.entities.question.toQuestionModel
import linKav20.github.com.entities.results.models.PassingChoice
import linKav20.github.com.entities.results.models.ResultModel
import linKav20.github.com.entities.results.tables.ResultEntity
import linKav20.github.com.entities.user.passings.findPassingByMail
import linKav20.github.com.entities.user.passings.tables.PassingEntity
import linKav20.github.com.entities.user.passings.toPassingsToString
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

    transaction {
        ResultEntity.new {
            passing = passingEntity
            question = questionEntity
            answer = answerEntity
        }
    }
}