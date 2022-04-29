package linKav20.github.com.answer.database

import org.jetbrains.exposed.dao.id.LongIdTable

object AnswersTable : LongIdTable() {
    val answerId = integer("answer_id").uniqueIndex().autoIncrement()
    val text = varchar("text", 500)
    val weight = integer("weight")
}