package linKav20.github.com.test.database

import linKav20.github.com.user.database.UserEntity
import linKav20.github.com.user.database.UsersTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class TestEntity (id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<TestEntity>(TestsTable)

    var testId by TestsTable.testId
    val name by TestsTable.name
    val description by TestsTable.description
    val creator by UserEntity referencedOn TestsTable.creator
    val responsible by UserEntity referencedOn TestsTable.responsible
    val creationDate by TestsTable.creationDate
    val lastModifiedDate by TestsTable.lastModifiedDate
    val lastModifiedUser by UserEntity referencedOn TestsTable.lastModifiedUser
    val testState by TestsTable.testState
}