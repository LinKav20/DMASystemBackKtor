package linKav20.github.com.entities.category.tables

import linKav20.github.com.entities.test.tables.TestEntity
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CategoryEntity (id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<CategoryEntity>(CategoriesTable)

    var categoryId by CategoriesTable.categoryId
    var name by CategoriesTable.name
    var test by TestEntity referencedOn CategoriesTable.test
}