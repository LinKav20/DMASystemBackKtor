package linKav20.github.com.entities.category.categotiesHierarchyTable

import linKav20.github.com.entities.category.tables.CategoryEntity
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CategoryHierarchyEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<CategoryHierarchyEntity>(CategoriesHierarchyTable)

    var parentCategory by CategoryEntity referencedOn CategoriesHierarchyTable.parentCategory
    var childCategory by CategoryEntity referencedOn CategoriesHierarchyTable.childCategory
}