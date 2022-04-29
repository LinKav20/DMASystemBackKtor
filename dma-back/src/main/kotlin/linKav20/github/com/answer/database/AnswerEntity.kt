package linKav20.github.com.answer.database

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AnswerEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<AnswerEntity>(AnswersTable)

    var answerId by AnswersTable.answerId
    var text by AnswersTable.text
    var weight by AnswersTable.weight
}
