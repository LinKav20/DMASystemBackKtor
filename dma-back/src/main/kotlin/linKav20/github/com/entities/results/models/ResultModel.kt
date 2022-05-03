package linKav20.github.com.entities.results.models

import kotlinx.serialization.Serializable

@Serializable
data class ResultModel(
    var mail: String,
    var results: List<PassingChoice>
)