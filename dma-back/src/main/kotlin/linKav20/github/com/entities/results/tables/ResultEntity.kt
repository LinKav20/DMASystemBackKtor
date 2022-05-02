package linKav20.github.com.entities.results.tables

import linKav20.github.com.entities.category.tables.CategoryEntity
import linKav20.github.com.entities.question.tables.QuestionEntity
import linKav20.github.com.entities.user.passings.tables.PassingEntity
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ResultEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<ResultEntity>(ResultsTable)

    val resultId by ResultsTable.resultId
    var question by QuestionEntity referencedOn ResultsTable.question
    var answer by CategoryEntity referencedOn ResultsTable.answer
    var passing by PassingEntity referencedOn ResultsTable.passing
}