package linKav20.github.com.entities.results.analyze.tables

import linKav20.github.com.entities.category.tables.CategoriesTable
import linKav20.github.com.entities.test.tables.TestsTable
import org.jetbrains.exposed.dao.id.LongIdTable

object AnalyzeTable : LongIdTable() {
    val analyzeId = integer("analyze_id").uniqueIndex().autoIncrement()
    val test = reference("test", TestsTable)
    val category = reference("category", CategoriesTable)
    val stats = integer("stats")
}