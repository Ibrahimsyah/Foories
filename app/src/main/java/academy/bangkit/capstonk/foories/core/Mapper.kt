package academy.bangkit.capstonk.foories.core

import academy.bangkit.capstonk.foories.core.data.source.local.entity.FoodEntity
import academy.bangkit.capstonk.foories.core.data.source.remote.response.CalorieResponse
import academy.bangkit.capstonk.foories.core.domain.model.Calories
import academy.bangkit.capstonk.foories.core.domain.model.Food

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
}