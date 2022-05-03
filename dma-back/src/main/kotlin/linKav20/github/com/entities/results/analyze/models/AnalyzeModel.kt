package linKav20.github.com.entities.results.analyze.models

import kotlinx.serialization.Serializable

@Serializable
data class AnalyzeModel(var parent: String, var category: String, var stats: Int);