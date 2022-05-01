package linKav20.github.com.entities.user

import linKav20.github.com.entities.user.models.UserModel
import linKav20.github.com.entities.user.tables.UserEntity
import linKav20.github.com.entities.user.tables.UsersTable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.math.BigInteger
import java.security.MessageDigest

private fun md5(input: String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}

fun findUser(user: UserModel): Boolean {
    val saved = transaction {
        (UserEntity.find { (UsersTable.login eq user.username) and (UsersTable.password eq md5(user.password)) }).count()
    }
    return saved == 1L
}

fun addUser(user: UserModel) {
    transaction {
        UserEntity.new {
            login = user.username
            password = md5(user.password)
        }
    }
}