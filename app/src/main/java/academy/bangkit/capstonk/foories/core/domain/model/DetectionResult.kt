package academy.bangkit.capstonk.foories.core.domain.model

data class DetectionResult(
    val name: String,
    val confidence: Double,
    val calorie : Double
)