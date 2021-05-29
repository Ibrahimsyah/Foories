package academy.bangkit.capstonk.foories.core.domain.model

data class DetectionResult(
    val foodName: String,
    val confidence: Double,
    val calorie : Double
)