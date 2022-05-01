package linKav20.github.com.entities.answer.tables

import linKav20.github.com.entities.question.tables.QuestionEntity
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AnswerEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<AnswerEntity>(AnswersTable)

    var answerId by AnswersTable.answerId
    var text by AnswersTable.text
    var weight by AnswersTable.weight
    var question by QuestionEntity referencedOn AnswersTable.question
}
