package linKav20.github.com.entities.user

import linKav20.github.com.entities.user.models.UserModel
import linKav20.github.com.entities.user.basicUser.tables.UserEntity
import linKav20.github.com.entities.user.basicUser.tables.UsersTable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import linKav20.github.com.core.security.*

fun findUser(user: UserModel): UserEntity? {
    val saved = transaction {
        val users =
            UserEntity.find { (UsersTable.login eq user.login) and (UsersTable.password eq md5(user.password)) }
        if (users.count() == 1L) {
            users.elementAt(0)
        } else {
            null
        }
    } ?: return null

    return saved
}

fun findUserByLogin(user: UserModel): UserEntity? {
    val saved = transaction {
        val users =
            UserEntity.find { (UsersTable.login eq user.login) }
        if (users.count() == 1L) {
            users.elementAt(0)
        } else {
            null
        }
    } ?: return null

    return saved
}

fun addUser(user: UserModel) {
    transaction {
        UserEntity.new {
            login = user.login
            password = md5(user.password)
        }
    }
}

fun toUserModel(userEntity: UserEntity) = UserModel(userEntity.login, userEntity.password)

fun getUsers(): List<UserModel> {
    val models = mutableListOf<UserModel>()
    transaction {
        val users = UserEntity.all()
        for (user in users) {
            models.add(toUserModel(user))
        }
    }
    return models
}
