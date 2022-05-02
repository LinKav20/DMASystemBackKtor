package linKav20.github.com.entities.results.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class PassingChoice(
    @SerializedName("question_id") var questionID: Long,
    @SerializedName("answer_id") var answerID: Long
)