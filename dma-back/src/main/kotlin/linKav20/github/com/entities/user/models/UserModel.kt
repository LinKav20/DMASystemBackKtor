package linKav20.github.com.entities.user.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.*

@Serializable
data class UserModel(
    @SerializedName("login") val login: String,
    @Transient val password: String = ""
);