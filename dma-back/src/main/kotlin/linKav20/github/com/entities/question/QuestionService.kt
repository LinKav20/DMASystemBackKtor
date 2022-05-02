package linKav20.github.com.entities.question

import linKav20.github.com.entities.answer.saveAnswers
import linKav20.github.com.entities.category.models.CategoryModel
import linKav20.github.com.entities.category.tables.CategoryEntity
import linKav20.github.com.entities.question.models.QuestionModel
import linKav20.github.com.entities.question.tables.QuestionEntity
import org.jetbrains.exposed.sql.transactions.transaction

fun saveQuestion(questionModel: QuestionModel, categoryEntity: CategoryEntity) {
    transaction {
        val questionEntity = createQuestion(questionModel, categoryEntity)
        saveAnswers(questionModel, questionEntity)
    }
}

fun createQuestion(questionModel: QuestionModel, categoryEntity: CategoryEntity) = transaction {
    QuestionEntity.new {
        question = questionModel.question
        category = categoryEntity
    }
}

fun saveQuestions(category: CategoryModel, categoryEntity: CategoryEntity) {
    if (category.questions != null) {
        for (question in category.questions) {
            saveQuestion(question, categoryEntity)
        }
    }
}