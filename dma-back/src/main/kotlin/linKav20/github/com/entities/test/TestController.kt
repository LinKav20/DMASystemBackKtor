package linKav20.github.com.entities.test

import linKav20.github.com.entities.category.saveCategory
import linKav20.github.com.entities.test.models.TestModel
import linKav20.github.com.entities.test.tables.TestEntity
import linKav20.github.com.entities.user.findUser
import org.jetbrains.exposed.sql.transactions.transaction

fun saveTest(test: TestModel) {
    transaction {
        val testEntity = TestEntity.new {
            name = test.name
            description = test.description
            creator = findUser(test.creator)!!
            responsible = findUser(test.responsible)!!
            creationDate = test.creationDate
            lastModifiedDate = test.lastModifiedDate
            lastModifiedUser = findUser(test.lastModifiedUser)!!
            testState = test.testState.toString()
        }
        saveCategory(test.categories, testEntity)
    }
}