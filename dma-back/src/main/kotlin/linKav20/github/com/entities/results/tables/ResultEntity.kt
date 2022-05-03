package linKav20.github.com.entities.results.tables

import linKav20.github.com.entities.answer.tables.AnswerEntity
import linKav20.github.com.entities.category.tables.CategoryEntity
import linKav20.github.com.entities.question.tables.QuestionEntity
import linKav20.github.com.entities.test.tables.TestEntity
import linKav20.github.com.entities.user.passings.tables.PassingEntity
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ResultEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<ResultEntity>(ResultsTable)

    val resultId by ResultsTable.resultId
    var test by TestEntity referencedOn ResultsTable.test
    var category by CategoryEntity referencedOn ResultsTable.category
    var question by QuestionEntity referencedOn ResultsTable.question
    var answer by  ResultsTable.answer
    var passing by PassingEntity referencedOn ResultsTable.passing
}