package linKav20.github.com.entities.category.categotiesHierarchy

import linKav20.github.com.entities.category.categotiesHierarchy.tables.CategoriesHierarchyTable
import linKav20.github.com.entities.category.categotiesHierarchy.tables.CategoryHierarchyEntity
import linKav20.github.com.entities.category.models.CategoryModel
import linKav20.github.com.entities.category.tables.CategoriesTable
import linKav20.github.com.entities.category.tables.CategoryEntity
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

fun addHierarchy(parent: CategoryEntity, child: CategoryEntity) {
    transaction {
        CategoryHierarchyEntity.new {
            parentCategory = parent
            childCategory = child
        }
    }
}

fun replaceSubCategories(categoryModels: List<CategoryModel>): List<CategoryModel> {
    val categories = mutableListOf<CategoryModel>()

    for (category in categoryModels) {
        if (categoryIsParent(category)) {
            setSubCategories(category, categoryModels)
            categories.add(category)
        }else if (!categoryIsChild(category)){
            categories.add(category)
        }
        println("${category.name} is parent: ${categoryIsParent(category)}")
    }
    return categories
}

private fun categoryIsParent(categoryModel: CategoryModel): Boolean {
    val query = queryParentCategory(categoryModel.id)
    val subcats = transaction {
        CategoryHierarchyEntity.wrapRows(query)
    }
    val answer = transaction {
        subcats.empty()
    }
    return !answer;
}

private fun queryParentCategory(id: Long) = transaction {
    CategoriesHierarchyTable.select {
        CategoriesHierarchyTable.parentCategory eq id
    }
}

private fun setSubCategories(categoryModel: CategoryModel, allCategories: List<CategoryModel>) {
    val entities = getSubcatsEntities(categoryModel.id)
    val categories = mutableListOf<CategoryModel>()

    transaction {
        for (entity in entities) {
            val category = findCategory(entity.categoryId.toLong(), allCategories)
            if (category != null) {
                categories.add(category)
            }
        }
    }

    categoryModel.questions = null
    categoryModel.categories = categories
}

private fun getSubcatsEntities(id: Long): List<CategoryEntity> {
    val query = querySQLSubCategories(id)
    val subcats = transaction {
        CategoryEntity.wrapRows(query).toList()
    }
    return subcats
}

private fun querySQLSubCategories(id: Long) = transaction {
    CategoriesTable.join(
        CategoriesHierarchyTable,
        JoinType.INNER,
        additionalConstraint = { CategoriesTable.categoryId eq CategoriesHierarchyTable.childCategory })
        .select {
            CategoriesHierarchyTable.parentCategory eq id
        }.withDistinct()
}

private fun findCategory(id: Long, categoryModels: List<CategoryModel>): CategoryModel? {
    val all = categoryModels.filter { it.id == id }
    if (all.isEmpty()) {
        return null
    }
    return all[0]
}

private fun queryChildCategory(id: Long) = transaction {
    CategoriesHierarchyTable.select {
        CategoriesHierarchyTable.childCategory eq id
    }
}

private fun categoryIsChild(categoryModel: CategoryModel): Boolean {
    val query = queryChildCategory(categoryModel.id)
    val subcats = transaction {
        CategoryHierarchyEntity.wrapRows(query)
    }
    val answer = transaction {
        subcats.empty()
    }
    return !answer;
}