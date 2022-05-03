package linKav20.github.com.entities.user.basicUser.tables

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserEntity (id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<UserEntity>(UsersTable)

    var userId by UsersTable.userId
    var login by UsersTable.login
    var password by UsersTable.password
}
