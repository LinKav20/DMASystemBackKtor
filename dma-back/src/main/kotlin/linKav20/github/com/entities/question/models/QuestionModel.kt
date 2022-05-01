package linKav20.github.com.entities.question.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import linKav20.github.com.entities.answer.models.AnswerModel

@Serializable
data class QuestionModel(
    @SerializedName("question") val question: String,
    @SerializedName("answers") val answers: List<AnswerModel>
)