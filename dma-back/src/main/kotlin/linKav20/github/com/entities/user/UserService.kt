package linKav20.github.com.entities.user

import linKav20.github.com.entities.user.models.UserModel
import linKav20.github.com.entities.user.tables.UserEntity
import linKav20.github.com.entities.user.tables.UsersTable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

fun findUser(user: UserModel): Boolean {
    val saved = transaction {
        (UserEntity.find { (UsersTable.login eq user.username) and (UsersTable.password eq user.password) }).count()
    }

    println(saved)
    return saved == 1L
}

fun addUser(user: UserModel) {
    transaction {
        UserEntity.new {
            login = user.username
            password = user.password
        }
    }
}