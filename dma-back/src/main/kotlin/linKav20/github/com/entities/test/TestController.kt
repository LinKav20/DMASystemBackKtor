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
import linKav20.github.com.entities.user.redactors.saveRedactors
import linKav20.github.com.entities.user.toUserModel
import org.jetbrains.exposed.sql.transactions.transaction

fun saveTest(test: TestModel) {
    transaction {
        val testEntity = TestEntity.new {
            name = test.name
            description = test.description
            creator = findUserByLogin(test.creator)!!
            responsible = findUserByLogin(test.responsible)!!
            creationDate = test.creationDate
            lastModifiedDate = test.lastModifiedDate
            lastModifiedUser = findUserByLogin(test.lastModifiedUser)!!
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
        redactors =  getRedactors(testEntity),
        passing = getPassings(testEntity)
    )
}

fun getTest(id: Int): TestModel? {
    val saved = transaction {
        val test =
            TestEntity.find { (TestsTable.testId eq id) }
        if (test.count() == 1L) {
            test.elementAt(0)
        } else {
            null
        }
    } ?: return null

    return toTestModel(saved)
}