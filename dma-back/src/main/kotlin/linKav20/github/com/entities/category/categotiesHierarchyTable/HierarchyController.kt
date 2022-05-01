package linKav20.github.com.entities.category.categotiesHierarchyTable

import linKav20.github.com.entities.category.categotiesHierarchyTable.tables.CategoryHierarchyEntity
import linKav20.github.com.entities.category.tables.CategoryEntity
import org.jetbrains.exposed.sql.transactions.transaction

fun addHierarchy(parent:CategoryEntity, child:CategoryEntity){
    transaction {
        CategoryHierarchyEntity.new {
            parentCategory = parent
            childCategory = child
        }
    }
}