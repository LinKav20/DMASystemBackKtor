package linKav20.github.com.entities.category.models

import linKav20.github.com.entities.answer.models.AnswerModel

data class CategoryModel(
    val id: Int,
    val name: String,
    val categories: List<CategoryModel>,
    val answers: List<AnswerModel>
)