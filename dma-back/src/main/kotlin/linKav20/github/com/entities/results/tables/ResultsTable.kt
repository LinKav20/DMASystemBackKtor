package linKav20.github.com.entities.results.tables

import linKav20.github.com.entities.answer.tables.AnswersTable
import linKav20.github.com.entities.question.tables.QuestionsTable
import linKav20.github.com.entities.user.passings.tables.PassingsTable
import org.jetbrains.exposed.dao.id.LongIdTable

object ResultsTable : LongIdTable() {
    val resultId = integer("result_id").uniqueIndex().autoIncrement()
    val passing = reference("passing", PassingsTable)
    val question = reference("question", QuestionsTable)
    val answer = reference("answer", AnswersTable)
}