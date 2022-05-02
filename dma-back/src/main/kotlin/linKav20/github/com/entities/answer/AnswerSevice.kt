package linKav20.github.com.entities.answer

import linKav20.github.com.entities.answer.models.AnswerModel
import linKav20.github.com.entities.answer.tables.AnswerEntity
import linKav20.github.com.entities.question.models.QuestionModel
import linKav20.github.com.entities.question.tables.QuestionEntity
import org.jetbrains.exposed.sql.transactions.transaction

fun saveAnswer(answerModel: AnswerModel, questionEntity: QuestionEntity) {
    transaction {
        AnswerEntity.new {
            text = answerModel.text
            weight = answerModel.weight
            question = questionEntity
        }
    }
}

fun saveAnswers(questionModel: QuestionModel, questionEntity: QuestionEntity) {
    for (answer in questionModel.answers) {
        //saveAnswer(answer, questionEntity)
    }
}