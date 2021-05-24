package academy.bangkit.capstonk.foories.core

import academy.bangkit.capstonk.foories.core.data.source.remote.response.CalorieResponse
import academy.bangkit.capstonk.foories.core.domain.model.Calories

object Mapper {
    fun calorieResponseToDomain(response: CalorieResponse) = Calories(response.calories)
}