package linKav20.github.com.entities.user.basicUser.tables

import org.jetbrains.exposed.dao.id.LongIdTable

object UsersTable : LongIdTable() {
    val userId = integer("user_id").uniqueIndex().autoIncrement()
    val login = varchar("login", 250).uniqueIndex()
    val password = varchar("password", 250)
}