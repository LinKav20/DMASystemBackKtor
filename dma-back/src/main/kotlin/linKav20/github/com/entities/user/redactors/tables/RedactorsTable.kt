package linKav20.github.com.entities.user.redactors.tables

import linKav20.github.com.entities.test.tables.TestsTable
import linKav20.github.com.entities.user.basicUser.tables.UsersTable
import org.jetbrains.exposed.dao.id.LongIdTable

object RedactorsTable: LongIdTable()  {
    val redactorId = integer("redactor_id").uniqueIndex().autoIncrement()
    val redactor = reference("redactor", UsersTable)
    val test = reference("test", TestsTable)
}