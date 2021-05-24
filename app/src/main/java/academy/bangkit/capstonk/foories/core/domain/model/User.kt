package academy.bangkit.capstonk.foories.core.domain.model

data class User(
    val name: String,
    val gender: String,
    val weight: Double,
    val height: Double,
    val age: Int,
    val activityType: Int
)
