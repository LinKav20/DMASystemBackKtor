package linKav20.github.com.entities.question.tables

import linKav20.github.com.entities.category.tables.CategoriesTable
import org.jetbrains.exposed.dao.id.LongIdTable

object QuestionsTable : LongIdTable() {
    val questionId = integer("question_id").uniqueIndex().autoIncrement()
    val question = varchar("question", 500)
    val category = reference("category", CategoriesTable)
}