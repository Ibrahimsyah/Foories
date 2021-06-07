package academy.bangkit.capstonk.foories.core.domain.repository

import academy.bangkit.capstonk.foories.core.data.source.remote.entity.FoodCaloriesPayload
import academy.bangkit.capstonk.foories.core.data.source.remote.response.DetectionResponse
import academy.bangkit.capstonk.foories.core.domain.model.Calories
import academy.bangkit.capstonk.foories.core.domain.model.Food
import academy.bangkit.capstonk.foories.core.domain.model.User
import androidx.lifecycle.LiveData

interface IFooriesRepository {
    suspend fun getUserCalories(user: User): Calories
    fun getUserTodayFoods(): LiveData<List<Food>>
    fun getHistory7Days(): LiveData<List<Food>>
    fun getHistory30Days(): LiveData<List<Food>>
    fun getUserTodayTotalCalories(): LiveData<Double>
    suspend fun insertFood(food: Food)
    suspend fun detectFoodCalories(foodCaloriesPayload: FoodCaloriesPayload): DetectionResponse
}