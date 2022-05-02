package linKav20.github.com.entities.results.models

import kotlinx.serialization.Serializable
import linKav20.github.com.entities.answer.models.AnswerModel
import linKav20.github.com.entities.question.models.QuestionModel

@Serializable
data class ResultModel(
    var mail: String,
    var results: List<PassingChoice>
)