package linKav20.github.com.core.databases

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database


fun Application.connectDataBase() {
    Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;", "org.h2.Driver")

    createTables()
}

