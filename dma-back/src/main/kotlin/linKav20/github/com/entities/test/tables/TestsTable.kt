package linKav20.github.com.entities.test.tables

import linKav20.github.com.entities.user.tables.UsersTable
import org.jetbrains.exposed.dao.id.LongIdTable

object TestsTable : LongIdTable() {
    val testId = integer("test_id").uniqueIndex().autoIncrement()
    val name = varchar("name", 50)
    val description = varchar("description", 500)
    val creator = reference("creator", UsersTable)
    val responsible = reference("responsible", UsersTable)
    val creationDate = varchar("creationDate", 50)
    val lastModifiedDate = varchar("lastModifiedDate", 50)
    val lastModifiedUser = reference("lastModifiedUser", UsersTable)
    val testState = varchar("testState", 50)
}