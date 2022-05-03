package linKav20.github.com.entities.user.redactors

import linKav20.github.com.entities.test.tables.TestEntity
import linKav20.github.com.entities.test.tables.TestsTable
import linKav20.github.com.entities.user.findUserByLogin
import linKav20.github.com.entities.user.models.UserModel
import linKav20.github.com.entities.user.redactors.tables.RedactorEntity
import linKav20.github.com.entities.user.redactors.tables.RedactorsTable
import linKav20.github.com.entities.user.basicUser.tables.UserEntity
import linKav20.github.com.entities.user.basicUser.tables.UsersTable
import linKav20.github.com.entities.user.toUserModel
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.select
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
            redactor = findUserByLogin(redactorModel)
                ?: throw NullPointerException("Cannot find user with login ${redactorModel.login} in system")
            test = testEntity
        }
    }
}

fun getRedactors(testEntity: TestEntity): List<UserModel> {
    val redactorsEntities = getRedactorsEntities(testEntity.testId.toLong())
    val redactors = toRedactorsModel(redactorsEntities)
    return redactors
}

fun getTestsByRedactorFromTable(userEntity: UserEntity): List<TestEntity> {
   return getTestsEntities(userEntity.userId.toLong())
}

fun toRedactorsModel(redactorsEntities: List<UserEntity>): List<UserModel> {
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

private fun getTestsEntities(id: Long): List<TestEntity> {
    val query = querySQLTestsByRedactor(id)
    val tests = transaction {
        TestEntity.wrapRows(query).toList()
    }
    return tests
}

private fun querySQLTestsByRedactor(id: Long) = transaction {
    TestsTable.join(
        RedactorsTable,
        JoinType.INNER,
        additionalConstraint = { TestsTable.testId eq RedactorsTable.test })
        .select {
            RedactorsTable.redactor eq id
        }.withDistinct()
}

private fun querySQLRedactors(id: Long) = transaction {
    UsersTable.join(
        RedactorsTable,
        JoinType.INNER,
        additionalConstraint = { UsersTable.userId eq RedactorsTable.redactor })
        .select {
            RedactorsTable.test eq id
        }.withDistinct()
}