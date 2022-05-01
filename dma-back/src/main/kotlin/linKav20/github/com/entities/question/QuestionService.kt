package linKav20.github.com.entities.question

import linKav20.github.com.entities.answer.saveAnswer
import linKav20.github.com.entities.category.tables.CategoryEntity
import linKav20.github.com.entities.question.models.QuestionModel
import linKav20.github.com.entities.question.tables.QuestionEntity
import org.jetbrains.exposed.sql.transactions.transaction

fun saveQuestion(questionModel: QuestionModel, categoryEntity: CategoryEntity) {
    transaction {
        val question = QuestionEntity.new {
            question = questionModel.question
            category = categoryEntity
        }

        for (answer in questionModel.answers) {
            saveAnswer(answer, question)
        }
    }
}