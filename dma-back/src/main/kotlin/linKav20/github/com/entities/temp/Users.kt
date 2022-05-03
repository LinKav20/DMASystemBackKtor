package linKav20.github.com.entities.temp

import linKav20.github.com.entities.user.basicUser.tables.UserEntity
import org.jetbrains.exposed.sql.transactions.transaction

fun addTestUsers(){
    transaction {
        UserEntity.new {
            login = "Admin"
            password = "21232f297a57a5a743894a0e4a801fc3"
        }

        UserEntity.new {
            login = "Lina"
            password = "f6f4deb7dad1c2e5e0b4d6569dc3c1c5"
        }

        UserEntity.new {
            login = "Kosta"
            password = "fb63d1547e84f764fe89476651b89897"
        }

        UserEntity.new {
            login = "Artem"
            password = "9605959095f1e07ba7628a197088bd70"
        }
    }
}