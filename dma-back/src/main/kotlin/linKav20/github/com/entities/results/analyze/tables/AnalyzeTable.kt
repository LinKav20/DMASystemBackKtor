package linKav20.github.com.entities.results.analyze.tables

import linKav20.github.com.entities.category.tables.CategoriesTable
import org.jetbrains.exposed.dao.id.LongIdTable

object AnalyzeTable : LongIdTable() {
    val analyzeId = integer("analyze_id").uniqueIndex().autoIncrement()
    val category = reference("category", CategoriesTable)
    val stats = integer("stats")
}