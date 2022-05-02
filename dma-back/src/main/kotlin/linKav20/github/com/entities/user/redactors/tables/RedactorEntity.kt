package linKav20.github.com.entities.user.redactors.tables

import linKav20.github.com.entities.test.tables.TestEntity
import linKav20.github.com.entities.user.tables.UserEntity
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class RedactorEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<RedactorEntity>(RedactorsTable)

    var redactorId by RedactorsTable.redactorId
    var redactor by UserEntity referencedOn RedactorsTable.redactor
    var test by TestEntity referencedOn RedactorsTable.test
}