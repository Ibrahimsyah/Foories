package academy.bangkit.capstonk.foories.core.util

import academy.bangkit.capstonk.foories.core.data.source.local.entity.FoodEntity
import academy.bangkit.capstonk.foories.core.data.source.remote.response.CalorieResponse
import academy.bangkit.capstonk.foories.core.domain.model.Calories
import academy.bangkit.capstonk.foories.core.domain.model.DetectionResult
import academy.bangkit.capstonk.foories.core.domain.model.Food
import java.util.*

object Mapper {
    fun calorieResponseToDomain(response: CalorieResponse) = Calories(response.calories)

    fun foodEntitiesToDomains(foods: List<FoodEntity>): List<Food> {
        return foods.map {
            Food(it.name, it.calories, it.addedAt)
        }
    }

    fun foodDomainToEntity(food: Food): FoodEntity {
        return FoodEntity(name = food.name, calories = food.calories, addedAt = food.addedAt)
    }

    fun reformatDetectionResult(list: List<DetectionResult>): List<DetectionResult> {
        return list.map { result ->
            DetectionResult(
                result.name.capitalizeWords("_"),
                result.confidence * 100,
                result.calorie
            )
        }
    }

    fun detectionResultToFood(detectionResult: DetectionResult): Food {
        val dateNow = Date()
        return Food(detectionResult.name, detectionResult.calorie, dateNow)
    }
}