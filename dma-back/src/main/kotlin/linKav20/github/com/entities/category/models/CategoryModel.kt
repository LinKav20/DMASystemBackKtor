package linKav20.github.com.entities.category.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import linKav20.github.com.entities.question.models.QuestionModel

@Serializable
data class CategoryModel(
    val id: Long,
    @SerializedName("name") var name: String,
    @SerializedName("categories") var categories: List<CategoryModel>?,
    @SerializedName("questions") var questions: List<QuestionModel>?
)