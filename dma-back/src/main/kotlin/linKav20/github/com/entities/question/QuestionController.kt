package linKav20.github.com.entities.question

import linKav20.github.com.entities.answer.deleteAllAnswers
import linKav20.github.com.entities.answer.getAnswers
import linKav20.github.com.entities.answer.saveAnswers
import linKav20.github.com.entities.category.models.CategoryModel
import linKav20.github.com.entities.category.tables.CategoryEntity
import linKav20.github.com.entities.question.models.QuestionModel
import linKav20.github.com.entities.question.tables.QuestionEntity
import linKav20.github.com.entities.question.tables.QuestionsTable
import org.jetbrains.exposed.sql.select
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
        for (question in category.questions!!) {
            saveQuestion(question, categoryEntity)
        }
    }
}

fun findQuestionById(id: Long) = transaction {
    QuestionEntity.findById(id)
}

fun getQuestions(categoryEntity: CategoryEntity): List<QuestionModel> {
    val questionEntities = getQuestionEntities(categoryEntity.categoryId.toLong())
    val questions = toQuestionModels(questionEntities)

    return questions
}

fun toQuestionModel(questionEntity: QuestionEntity) = transaction {
    QuestionModel(
        id = questionEntity.questionId,
        question = questionEntity.question,
        answers = getAnswers(questionEntity)
    )
}

private fun toQuestionModels(questionEntities: List<QuestionEntity>): List<QuestionModel> {
    val questions = mutableListOf<QuestionModel>()

    for (question in questionEntities) {
        questions.add(toQuestionModel(question))
    }
    return questions
}

fun deleteAllQuestions(categoryEntity: CategoryEntity) {
    deleteAll(categoryEntity.categoryId.toLong())
}

private fun deleteAll(id: Long) {
    val questions = getQuestionEntities(id)
    val ids = getIds(questions)
    transaction {
        for (iD in ids) {
            val question = (QuestionEntity.findById(iD))
            if (question != null) {
                deleteAllAnswers(question)
                question.delete()
            }
        }
    }
}

private fun getIds(questionEntities: List<QuestionEntity>): MutableList<Long> {
    val ids = mutableListOf<Long>()
    transaction {
        for (question in questionEntities) {
            ids.add(question.questionId.toLong())
        }
    }
    return ids
}

private fun getQuestionEntities(id: Long): List<QuestionEntity> {
    val query = querySQLQuestions(id)
    val questionEntities = transaction {
        QuestionEntity.wrapRows(query).toList()
    }

    return questionEntities
}

private fun querySQLQuestions(id: Long) = transaction {
    QuestionsTable
        .select {
            QuestionsTable.category eq id
        }
}