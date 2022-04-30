package linKav20.github.com.entities.question.tables

import org.jetbrains.exposed.dao.id.LongIdTable

object QuestionsTable : LongIdTable() {
    val questionId = integer("question_id").uniqueIndex().autoIncrement()
    val question = varchar("question", 500)
}