package linKav20.github.com.entities.category.categotiesHierarchyTable

import linKav20.github.com.entities.category.tables.CategoriesTable
import org.jetbrains.exposed.dao.id.LongIdTable

object CategoriesHierarchyTable: LongIdTable() {
    val parentCategory = reference("parent_category", CategoriesTable)
    val childCategory = reference("child_category", CategoriesTable)
}