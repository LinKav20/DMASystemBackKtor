package linKav20.github.com.entities.temp

import linKav20.github.com.entities.user.tables.UserEntity
import org.jetbrains.exposed.sql.transactions.transaction

fun addTestUsers(){
    transaction {
        UserEntity.new {
            login = "Admin"
            password = "admin"
        }

        UserEntity.new {
            login = "Lina"
            password = "lina"
        }

        UserEntity.new {
            login = "Kosta"
            password = "kosta"
        }

        UserEntity.new {
            login = "Artem"
            password = "artem"
        }
    }
}