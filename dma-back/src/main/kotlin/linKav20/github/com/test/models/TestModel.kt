package linKav20.github.com.test.models

import linKav20.github.com.test.TestState
import linKav20.github.com.user.models.UserModel
import java.util.*

data class TestModel(
    val id: Int,
    val name: String,
    val description: String,
    val creator: UserModel,
    val responsible: UserModel,
    val creationDate: Date,
    val lastModifiedDate: Date,
    val lastModifiedUser: UserModel,
    val testState: TestState
);