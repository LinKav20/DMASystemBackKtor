package linKav20.github.com.entities.answer

import linKav20.github.com.entities.answer.models.AnswerModel
import linKav20.github.com.entities.answer.tables.AnswerEntity
import linKav20.github.com.entities.answer.tables.AnswersTable
import linKav20.github.com.entities.question.models.QuestionModel
import linKav20.github.com.entities.question.tables.QuestionEntity
import org.jetbrains.exposed.sql.select
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
        saveAnswer(answer, questionEntity)
    }
}

fun getAnswers(questionEntity: QuestionEntity): List<AnswerModel> {
    val answersEntities = getAnswerEntities(questionEntity.questionId.toLong())
    val answers = toAnswerModels(answersEntities)

    return answers
}

fun findAnswerById(id: Long): AnswerEntity? {
    val answer = transaction {
        AnswerEntity.findById(id)
    }
    return answer
}

fun toAnswerModel(answerEntity: AnswerEntity) =
    AnswerModel(id = answerEntity.answerId, text = answerEntity.text, weight = answerEntity.weight)

fun toAnswerModels(answerEntities: List<AnswerEntity>): List<AnswerModel> {
    val answers = mutableListOf<AnswerModel>()

    for (answerEntity in answerEntities) {
        answers.add(toAnswerModel(answerEntity))
    }

    return answers
}

fun deleteAllAnswers(questionEntity: QuestionEntity) {
    val answers = getAnswerEntities(questionEntity.questionId.toLong())
    val ids = getIds(answers)
    for (id in ids) {
        transaction {
            (AnswerEntity.findById(id))?.delete()
        }
    }
}

private fun getIds(answerEntities: List<AnswerEntity>): MutableList<Long> {
    val ids = mutableListOf<Long>()
    for (answer in answerEntities) {
        transaction {
            ids.add(answer.answerId.toLong())
        }
    }
    return ids;
}

private fun getAnswerEntities(id: Long): List<AnswerEntity> {
    val query = querySQLAnswers(id)
    val answers = transaction {
        AnswerEntity.wrapRows(query).toList()
    }

    return answers
}

private fun querySQLAnswers(id: Long) = transaction {
    AnswersTable.select {
        AnswersTable.question eq id
    }
}