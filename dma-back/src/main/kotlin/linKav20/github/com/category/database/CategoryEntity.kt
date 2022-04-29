package linKav20.github.com.category.database

import linKav20.github.com.test.database.TestEntity
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CategoryEntity (id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<CategoryEntity>(CategoriesTable)

    val categoryId by CategoriesTable.categoryId
    val name by CategoriesTable.name
    val test by TestEntity referencedOn CategoriesTable.test
}