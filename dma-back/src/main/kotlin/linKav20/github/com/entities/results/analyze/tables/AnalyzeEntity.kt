package linKav20.github.com.entities.results.analyze.tables

import linKav20.github.com.entities.category.tables.CategoryEntity
import linKav20.github.com.entities.test.tables.TestEntity
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AnalyzeEntity (id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<AnalyzeEntity>(AnalyzeTable)

    val analyzeId by AnalyzeTable.analyzeId
    var test by TestEntity referencedOn AnalyzeTable.test
    var category by CategoryEntity referencedOn AnalyzeTable.category
    var stats by AnalyzeTable.stats
}