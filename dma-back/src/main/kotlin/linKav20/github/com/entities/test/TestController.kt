package linKav20.github.com.entities.test

import linKav20.github.com.entities.category.getCategories
import linKav20.github.com.entities.category.saveCategory
import linKav20.github.com.entities.test.models.TestModel
import linKav20.github.com.entities.test.tables.TestEntity
import linKav20.github.com.entities.test.tables.TestsTable
import linKav20.github.com.entities.user.findUserByLogin
import linKav20.github.com.entities.user.passings.getPassings
import linKav20.github.com.entities.user.passings.savePassings
import linKav20.github.com.entities.user.redactors.getRedactors
import linKav20.github.com.entities.user.redactors.getTestsByRedactorFromTable
import linKav20.github.com.entities.user.redactors.saveRedactors
import linKav20.github.com.entities.user.basicUser.tables.UserEntity
import linKav20.github.com.entities.user.passings.updatePassings
import linKav20.github.com.entities.user.toUserModel
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

fun saveTest(test: TestModel) {
    transaction {
        val testEntity = TestEntity.new {
            name = test.name
            description = test.description
            creator = findUserByLogin(test.creator)
                ?: throw NullPointerException("Cannot find user with login ${test.creator.login} in system")
            responsible = findUserByLogin(test.responsible)
                ?: throw NullPointerException("Cannot find user with login ${test.responsible.login} in system")
            creationDate = test.creationDate
            lastModifiedDate = test.lastModifiedDate
            lastModifiedUser = findUserByLogin(test.lastModifiedUser)
                ?: throw NullPointerException("Cannot find user with login ${test.lastModifiedUser.login} in system")
            testState = test.testState.toString()
        }
        savePassings(test.passing, testEntity)
        saveRedactors(test.redactors, testEntity)
        saveCategory(test.categories, testEntity)
    }
}

fun toTestModel(testEntity: TestEntity): TestModel = transaction {
    TestModel(
        name = testEntity.name,
        description = testEntity.description,
        creator = toUserModel(testEntity.creator),
        responsible = toUserModel(testEntity.responsible),
        creationDate = testEntity.creationDate,
        lastModifiedDate = testEntity.lastModifiedDate,
        lastModifiedUser = toUserModel(testEntity.responsible),
        testState = TestState.valueOf(testEntity.testState),
        categories = getCategories(testEntity),
        redactors = getRedactors(testEntity),
        passing = getPassings(testEntity)
    )
}

fun getTest(id: Int): TestModel {
    val saved = transaction {
        val test =
            TestEntity.find { (TestsTable.testId eq id) }
        if (test.count() == 1L) {
            test.elementAt(0)
        } else {
            null
        }
    } ?: throw NullPointerException("Cannot find test with ID $id in system")

    return toTestModel(saved)
}

fun updateTest(id: Int, newTest: TestModel) {
    var test = findTestById(id.toLong()) ?: throw NullPointerException("Cannot find test with ID $id in the system")
    updateTest(test, newTest)
}

fun getTestsByCreator(userEntity: UserEntity): List<TestModel> {
    val testEntities = getAllTestsByCreator(userEntity)
    val tests = toTestModels(testEntities)
    return tests
}

fun getTestsByResponsible(userEntity: UserEntity): List<TestModel> {
    val testEntities = getAllTestsByResponsible(userEntity)
    val tests = toTestModels(testEntities)
    return tests
}

fun getTestsByRedactor(userEntity: UserEntity): List<TestModel> {
    val testEntities = getAllTestsByRedactor(userEntity)
    val tests = toTestModels(testEntities)
    return tests
}

private fun toTestModels(testEntity: List<TestEntity>): List<TestModel> {
    val tests = mutableListOf<TestModel>()

    for (test in testEntity) {
        tests.add(toTestModel(test))
    }
    return tests
}

private fun getAllTestsByRedactor(userEntity: UserEntity): List<TestEntity> {
    val tests = getTestsByRedactorFromTable(userEntity)

    return tests
}

private fun getAllTestsByResponsible(userEntity: UserEntity): List<TestEntity> {
    val query = querySQLResponsibleTest(userEntity.userId.toLong())
    val tests = transaction {
        TestEntity.wrapRows(query).toList()
    }

    return tests
}

private fun getAllTestsByCreator(userEntity: UserEntity): List<TestEntity> {
    val query = querySQLCreatorTest(userEntity.userId.toLong())
    val tests = transaction {
        TestEntity.wrapRows(query).toList()
    }

    return tests
}

private fun querySQLCreatorTest(id: Long) = transaction {
    TestsTable.select {
        TestsTable.creator eq id
    }
}

private fun querySQLResponsibleTest(id: Long) = transaction {
    TestsTable.select {
        TestsTable.responsible eq id
    }
}

private fun findTestById(id: Long) = transaction {
    TestEntity.findById(id)
}

private fun updateTest(testEntity: TestEntity, test: TestModel) {
    transaction {
        testEntity.name = test.name
        testEntity.description = test.description
        testEntity.creator = findUserByLogin(test.creator)
            ?: throw NullPointerException("Cannot find user with login ${test.creator.login} in system")
        testEntity.responsible = findUserByLogin(test.responsible)
            ?: throw NullPointerException("Cannot find user with login ${test.responsible.login} in system")
        testEntity.creationDate = test.creationDate
        testEntity.lastModifiedDate = test.lastModifiedDate
        testEntity.lastModifiedUser = findUserByLogin(test.lastModifiedUser)
            ?: throw NullPointerException("Cannot find user with login ${test.lastModifiedUser.login} in system")
        testEntity.testState = test.testState.toString()
        updatePassings(test.passing, testEntity)
        //updateRedactors(test.redactors, testEntity)
        //updateCategory(test.categories, testEntity)
    }
}
