package linKav20.github.com.entities.user.basicUser.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.*

@Serializable
data class UserModel(
    @SerializedName("login") val login: String,
    val password: String = ""
);