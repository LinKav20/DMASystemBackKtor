package linKav20.github.com.entities.user.passings.tables

import linKav20.github.com.entities.test.tables.TestEntity
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PassingEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<PassingEntity>(PassingsTable)

    var redactorId by PassingsTable.passingId
    var mail by PassingsTable.mail
    var test by TestEntity referencedOn PassingsTable.test
}