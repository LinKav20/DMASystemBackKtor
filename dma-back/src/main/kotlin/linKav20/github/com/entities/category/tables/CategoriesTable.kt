package linKav20.github.com.entities.category.tables

import linKav20.github.com.entities.test.tables.TestsTable
import org.jetbrains.exposed.dao.id.LongIdTable

object CategoriesTable: LongIdTable() {
    val categoryId = integer("category_id").uniqueIndex().autoIncrement()
    val name = varchar("text", 500)
    val test = reference("test", TestsTable)
}