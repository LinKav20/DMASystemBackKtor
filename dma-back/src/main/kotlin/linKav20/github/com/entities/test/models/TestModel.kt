package linKav20.github.com.entities.test.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import linKav20.github.com.entities.category.models.CategoryModel
import linKav20.github.com.entities.test.TestState
import linKav20.github.com.entities.user.basicUser.models.UserModel

@Serializable
data class TestModel(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("creator") val creator: UserModel,
    @SerializedName("responsible") val responsible: UserModel,
    @SerializedName("redactors") val redactors: List<UserModel>,
    @SerializedName("categories") val categories: List<CategoryModel>,
    @SerializedName("creation_date") val creationDate: String,
    @SerializedName("last_modified_date") val lastModifiedDate: String,
    @SerializedName("last_modified_person") val lastModifiedUser: UserModel,
    @SerializedName("test_status") val testState: TestState,
    @SerializedName("passing") val passing:  List<String>
);