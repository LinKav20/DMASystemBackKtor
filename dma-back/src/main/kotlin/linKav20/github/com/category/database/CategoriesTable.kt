package linKav20.github.com.category.database

import linKav20.github.com.test.database.TestsTable
import org.jetbrains.exposed.dao.id.LongIdTable

object CategoriesTable: LongIdTable() {
    val categoryId = integer("category_id").uniqueIndex().autoIncrement()
    val name = varchar("text", 500)
    val test = reference("test", TestsTable)
}