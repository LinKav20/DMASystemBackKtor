package linKav20.github.com.entities.category

import linKav20.github.com.entities.category.categotiesHierarchyTable.addHierarchy
import linKav20.github.com.entities.category.models.CategoryModel
import linKav20.github.com.entities.category.tables.CategoriesTable
import linKav20.github.com.entities.category.tables.CategoryEntity
import linKav20.github.com.entities.question.getQuestions
import linKav20.github.com.entities.question.saveQuestions
import linKav20.github.com.entities.test.tables.TestEntity
import org.jetbrains.exposed.sql.select
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

fun getCategories(testEntity: TestEntity): List<CategoryModel> {
    val categoryEntities = getCategoriesEntities(testEntity.testId.toLong())
    val categories = toCategoryModels(categoryEntities)

    return categories
}

fun toCategoryModel(categoryEntity: CategoryEntity): CategoryModel {
    val category = transaction {
        CategoryModel(
            name = categoryEntity.name,
            categories = null,
            questions = getQuestions(categoryEntity)
        )
    }
    return category
}

fun toCategoryModels(categoryEntities: List<CategoryEntity>): List<CategoryModel> {
    val categories = mutableListOf<CategoryModel>()

    for (category in categoryEntities) {
        categories.add(toCategoryModel(category))
    }

    return categories
}

private fun getCategoriesEntities(id: Long): List<CategoryEntity> {
    val query = querySQLCategories(id)
    val categoryEntities = transaction {
        CategoryEntity.wrapRows(query).toList()
    }

    return categoryEntities
}

private fun querySQLCategories(id: Long) = transaction {
    CategoriesTable
        .select {
            CategoriesTable.test eq id
        }
}
