package linKav20.github.com.entities.results.tables

import linKav20.github.com.entities.answer.tables.AnswersTable
import linKav20.github.com.entities.question.tables.QuestionsTable
import linKav20.github.com.entities.user.passings.tables.PassingsTable
import linKav20.github.com.entities.category.tables.CategoriesTable
import linKav20.github.com.entities.test.tables.TestsTable
import org.jetbrains.exposed.dao.id.LongIdTable

object ResultsTable : LongIdTable() {
    val resultId = integer("result_id").uniqueIndex().autoIncrement()
    val passing = reference("passing", PassingsTable)
    val test = reference("test", TestsTable)
    val category = reference("category", CategoriesTable)
    val question = reference("question", QuestionsTable)
    val answer = integer("answer_weight")
}