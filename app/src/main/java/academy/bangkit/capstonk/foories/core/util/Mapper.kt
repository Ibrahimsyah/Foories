package academy.bangkit.capstonk.foories.core.util

import academy.bangkit.capstonk.foories.core.brain.Result
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

    fun detectionResultToDomain(list: List<Result>): List<DetectionResult> {
        return list.map {
            val confidence = it.confidence ?: 0f
            val foodName = it.title ?: ""
            val optimizedName = foodName.capitalizeWords("_")
            DetectionResult(optimizedName, confidence * 100.0, 1000.0)
        }
    }

    fun detectionResultToFood(detectionResult: DetectionResult): Food {
        val dateNow = Date()
        return Food(detectionResult.name, detectionResult.calorie, dateNow)
    }
}