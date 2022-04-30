package linKav20.github.com.core.databases

import linKav20.github.com.entities.category.categotiesHierarchyTable.CategoriesHierarchyTable
import linKav20.github.com.entities.category.tables.CategoriesTable
import linKav20.github.com.entities.question.tables.QuestionsTable
import linKav20.github.com.entities.test.tables.TestsTable
import linKav20.github.com.entities.user.tables.UsersTable
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun CreateTables() {
    transaction {
        SchemaUtils.create(UsersTable)
        SchemaUtils.create(CategoriesTable)
        SchemaUtils.create(CategoriesHierarchyTable)
        SchemaUtils.create(QuestionsTable)
        SchemaUtils.create(TestsTable)
    }
}