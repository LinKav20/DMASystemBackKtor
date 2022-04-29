package linKav20.github.com.user.database

import linKav20.github.com.answer.database.AnswersTable.uniqueIndex
import org.jetbrains.exposed.dao.id.LongIdTable

object UsersTable : LongIdTable() {
    val userId = integer("user_id").uniqueIndex().autoIncrement()
    val login = varchar("login", 250)
    val password = varchar("password", 250)
}