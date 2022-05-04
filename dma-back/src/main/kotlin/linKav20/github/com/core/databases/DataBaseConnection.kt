package linKav20.github.com.core.databases

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database


fun Application.connectDataBase() {
    //Database.connect("jdbc:postgresql://localhost:5432/dmadatabase", driver = "org.postgresql.Driver",
       // user = "postgres", password = "admin")

    Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;", "org.h2.Driver")

    println("Successfully connected to PostgreSQL")

    createTables()
}

