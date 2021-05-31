package academy.bangkit.capstonk.foories.core.data.source.remote.response

import academy.bangkit.capstonk.foories.core.domain.model.DetectionResult

data class DetectionResponse(
    val status: String,
    val foodsCalories: List<DetectionResult>
)
