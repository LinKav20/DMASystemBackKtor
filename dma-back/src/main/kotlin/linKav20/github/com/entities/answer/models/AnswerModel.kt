package linKav20.github.com.entities.answer.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AnswerModel(
    @SerializedName("answer_id") val id: Int,
    @SerializedName("text") val text: String,
    @SerializedName("weight") val weight: Int
);