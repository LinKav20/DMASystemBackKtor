package linKav20.github.com.entities.question.tables

import linKav20.github.com.entities.category.tables.CategoryEntity
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class QuestionEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<QuestionEntity>(QuestionsTable)

    val questionId by QuestionsTable.questionId
    var question by QuestionsTable.question
    var category by CategoryEntity referencedOn QuestionsTable.category
}