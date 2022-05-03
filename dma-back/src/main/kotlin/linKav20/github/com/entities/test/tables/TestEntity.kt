package linKav20.github.com.entities.test.tables

import linKav20.github.com.entities.user.basicUser.tables.UserEntity
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class TestEntity (id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<TestEntity>(TestsTable)

    var testId by TestsTable.testId
    var name by TestsTable.name
    var description by TestsTable.description
    var creator by UserEntity referencedOn TestsTable.creator
    var responsible by UserEntity referencedOn TestsTable.responsible
    var creationDate by TestsTable.creationDate
    var lastModifiedDate by TestsTable.lastModifiedDate
    var lastModifiedUser by UserEntity referencedOn TestsTable.lastModifiedUser
    var testState by TestsTable.testState
}