package linKav20.github.com.core.databases

import linKav20.github.com.entities.answer.tables.AnswersTable
import linKav20.github.com.entities.category.categotiesHierarchy.tables.CategoriesHierarchyTable
import linKav20.github.com.entities.category.tables.CategoriesTable
import linKav20.github.com.entities.question.tables.QuestionsTable
import linKav20.github.com.entities.results.analyze.tables.AnalyzeTable
import linKav20.github.com.entities.results.tables.ResultsTable
import linKav20.github.com.entities.test.tables.TestsTable
import linKav20.github.com.entities.user.passings.tables.PassingsTable
import linKav20.github.com.entities.user.redactors.tables.RedactorsTable
import linKav20.github.com.entities.user.basicUser.tables.UsersTable
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction


fun createTables() {
    transaction {
        SchemaUtils.create(UsersTable)
        SchemaUtils.create(CategoriesTable)
        SchemaUtils.create(CategoriesHierarchyTable)
        SchemaUtils.create(QuestionsTable)
        SchemaUtils.create(TestsTable)
        SchemaUtils.create(RedactorsTable)
        SchemaUtils.create(AnswersTable)
        SchemaUtils.create(PassingsTable)
        SchemaUtils.create(ResultsTable)
        SchemaUtils.create(AnalyzeTable)
    }
}