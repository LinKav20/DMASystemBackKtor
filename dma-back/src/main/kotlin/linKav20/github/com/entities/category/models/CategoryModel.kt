package linKav20.github.com.entities.category.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import linKav20.github.com.entities.question.models.QuestionModel

@Serializable
data class CategoryModel(
    @SerializedName("name") val name: String,
    @SerializedName("categories") val categories: List<CategoryModel>?,
    @SerializedName("questions") val questions: List<QuestionModel>?
)