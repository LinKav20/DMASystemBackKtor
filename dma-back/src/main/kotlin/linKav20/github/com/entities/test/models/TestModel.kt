package linKav20.github.com.entities.test.models

import linKav20.github.com.entities.category.models.CategoryModel
import linKav20.github.com.entities.test.TestState
import linKav20.github.com.entities.user.models.UserModel
import java.util.*

data class TestModel(
    val id: Int,
    val name: String,
    val description: String,
    val creator: UserModel,
    val responsible: UserModel,
    val redactors: List<UserModel>,
    val categories: List<CategoryModel>,
    val creationDate: Date,
    val lastModifiedDate: Date,
    val lastModifiedUser: UserModel,
    val testState: TestState
);