package linKav20.github.com.entities.user.models

import kotlinx.serialization.*

@Serializable
data class UserModel(val username: String, val password: String);