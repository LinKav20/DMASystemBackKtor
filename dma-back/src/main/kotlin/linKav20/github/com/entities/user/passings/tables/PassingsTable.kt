package linKav20.github.com.entities.user.passings.tables

import linKav20.github.com.entities.test.tables.TestsTable
import org.jetbrains.exposed.dao.id.LongIdTable

object PassingsTable: LongIdTable() {
    val passingId = integer("passing_id").uniqueIndex().autoIncrement()
    val mail = varchar("mail", 50)
    val test = reference("test", TestsTable)
}