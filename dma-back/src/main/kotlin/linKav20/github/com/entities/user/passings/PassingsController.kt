package linKav20.github.com.entities.user.passings

import linKav20.github.com.entities.test.tables.TestEntity
import linKav20.github.com.entities.user.passings.tables.PassingEntity
import linKav20.github.com.entities.user.passings.tables.PassingsTable
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

fun savePassings(passings: List<String>, testEntity: TestEntity) {
    transaction {
        for (passing in passings) {
            savePassing(passing, testEntity)
        }
    }
}

fun savePassing(passings: String, testEntity: TestEntity) {
    transaction {
        PassingEntity.new {
            mail = passings
            test = testEntity
        }
    }
}

fun getPassings(testEntity: TestEntity): List<String> {
    val passingEntities = getPassingsEntities(testEntity.testId.toLong())
    val passings = toPassingsToString(passingEntities)
    return passings
}

fun toPassingsToString(passingEntities: List<PassingEntity>): List<String> {
    val passings = mutableListOf<String>()

    transaction {
        for (passingEntity in passingEntities) {
            passings.add(passingEntity.mail)
        }
    }

    return passings
}

fun findPassingByMail(mail: String): PassingEntity? {
    val query = querySQLFindPassingByMail(mail)
    val passings = transaction {
        PassingEntity.wrapRows(query).toList()
    }
    val passing = transaction {
        if (passings.isEmpty()) null else passings[0]
    }
    return passing
}

private fun getPassingsEntities(id: Long): List<PassingEntity> {
    val query = querySQLPassings(id)
    val passingEntities = transaction {
        PassingEntity.wrapRows(query).toList()
    }

    return passingEntities
}

private fun querySQLFindPassingByMail(mail: String) = transaction {
    PassingsTable.select { PassingsTable.mail eq mail }
}


private fun querySQLPassings(id: Long) = transaction {
    PassingsTable.select {
        PassingsTable.test eq id
    }
}