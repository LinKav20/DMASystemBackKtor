package linKav20.github.com.entities.category

import linKav20.github.com.entities.category.categotiesHierarchyTable.addHierarchy
import linKav20.github.com.entities.category.models.CategoryModel
import linKav20.github.com.entities.category.tables.CategoryEntity
import linKav20.github.com.entities.question.saveQuestions
import linKav20.github.com.entities.test.tables.TestEntity
import org.jetbrains.exposed.sql.transactions.transaction

fun saveCategory(categories: List<CategoryModel>, testEntity: TestEntity) {
    for (category in categories) {
        transaction {
            saveCategory(category, testEntity)
        }
    }
}

fun saveCategory(category: CategoryModel, testEntity: TestEntity) {
    transaction {
        val parent = createCategory(category, testEntity)

        if (category.categories == null || category.categories.isEmpty()) {
            saveQuestions(category, parent)
        } else {
            for (subCategory in category.categories) {
                val child = createCategory(subCategory, testEntity)
                saveQuestions(subCategory, child)
                addHierarchy(parent, child)
            }
        }
    }
}

fun createCategory(category: CategoryModel, testEntity: TestEntity) = transaction {
    CategoryEntity.new {
        name = category.name
        test = testEntity
    }
}