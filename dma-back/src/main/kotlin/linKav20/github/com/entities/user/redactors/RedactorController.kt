package linKav20.github.com.entities.user.redactors

import linKav20.github.com.entities.test.tables.TestEntity
import linKav20.github.com.entities.test.tables.TestsTable
import linKav20.github.com.entities.user.findUserByLogin
import linKav20.github.com.entities.user.models.UserModel
import linKav20.github.com.entities.user.redactors.tables.RedactorEntity
import linKav20.github.com.entities.user.redactors.tables.RedactorsTable
import linKav20.github.com.entities.user.tables.UserEntity
import linKav20.github.com.entities.user.tables.UsersTable
import linKav20.github.com.entities.user.toUserModel
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun saveRedactors(redactors: List<UserModel>, testEntity: TestEntity) {
    for (redactor in redactors) {
        transaction {
            saveRedactor(redactor, testEntity)
        }
    }
}

fun saveRedactor(redactorModel: UserModel, testEntity: TestEntity) {
    transaction {
        RedactorEntity.new {
            redactor = findUserByLogin(redactorModel)!!
            test = testEntity
        }
    }
}

fun getRedactors(testEntity: TestEntity): List<UserModel> {
    val redactorsEntities = getRedactorsEntities(testEntity.testId.toLong())
    val redactors = toRedactorsModel(redactorsEntities)
    return redactors
}

fun toRedactorsModel(redactorsEntities: List<UserEntity>):List<UserModel>{
    val redactors = mutableListOf<UserModel>()

    transaction {
        for (redactorEntity in redactorsEntities) {
            redactors.add(toUserModel(redactorEntity))
        }
    }

    return redactors
}

private fun getRedactorsEntities(id: Long): List<UserEntity> {
    val query = querySQLRedactors(id)
    val redactorsEntities = transaction {
        UserEntity.wrapRows(query).toList()
    }

    return redactorsEntities
}

private fun querySQLRedactors(id:Long) = transaction {
    UsersTable.join(
        RedactorsTable,
        JoinType.INNER,
        additionalConstraint = { UsersTable.userId eq RedactorsTable.redactorId })
        .select {
            RedactorsTable.test eq id
        }.withDistinct()
}