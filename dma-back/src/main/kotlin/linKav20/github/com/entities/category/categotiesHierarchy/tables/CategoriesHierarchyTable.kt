package linKav20.github.com.entities.category.categotiesHierarchy.tables

import linKav20.github.com.entities.category.tables.CategoriesTable
import linKav20.github.com.entities.category.tables.CategoriesTable.autoIncrement
import linKav20.github.com.entities.category.tables.CategoriesTable.uniqueIndex
import org.jetbrains.exposed.dao.id.LongIdTable

object CategoriesHierarchyTable: LongIdTable() {
    val hierarchyId = integer("hierarchy_id").uniqueIndex().autoIncrement()
    val parentCategory = reference("parent_category", CategoriesTable)
    val childCategory = reference("child_category", CategoriesTable)
}