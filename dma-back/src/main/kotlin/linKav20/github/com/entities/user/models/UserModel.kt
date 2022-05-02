package linKav20.github.com.entities.user.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.*
import java.lang.reflect.Modifier

@Serializable
data class UserModel(
    @SerializedName("login") val login: String,
    val password: String = ""
);