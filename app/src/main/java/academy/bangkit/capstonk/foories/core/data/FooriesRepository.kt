package academy.bangkit.capstonk.foories.core.data

import academy.bangkit.capstonk.foories.core.data.source.local.CaloriesBank
import academy.bangkit.capstonk.foories.core.data.source.local.LocalDataSource
import academy.bangkit.capstonk.foories.core.data.source.remote.RemoteDataSource
import academy.bangkit.capstonk.foories.core.data.source.remote.entity.FoodCaloriesPayload
import academy.bangkit.capstonk.foories.core.data.source.remote.response.DetectionResponse
import academy.bangkit.capstonk.foories.core.domain.model.Calories
import academy.bangkit.capstonk.foories.core.domain.model.DetectionResult
import academy.bangkit.capstonk.foories.core.domain.model.Food
import academy.bangkit.capstonk.foories.core.domain.model.User
import academy.bangkit.capstonk.foories.core.domain.repository.IFooriesRepository
import academy.bangkit.capstonk.foories.core.util.Mapper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

class FooriesRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : IFooriesRepository {
    override suspend fun getUserCalories(user: User): Calories {
        val result = remoteDataSource.getUserCaloriesNeed(user)
        return Mapper.calorieResponseToDomain(result)
    }

    override fun getUserCaloriesLocal(user: User): Calories {
        val activityValue = when (user.activityType) {
            1 -> 1.2
            2 -> 1.375
            3 -> 1.55
            4 -> 1.725
            5 -> 1.9
            else -> 0.0
        }
        val calories =
            if (user.gender == "male") {
                ((10 * user.weight) + (6.25 * user.height) - (5 * user.age) + 5) * activityValue
            } else ((10 * user.weight) + (6.25 * user.height) - (5 * user.age) - 161) * activityValue

        return Calories(calories)
    }

    override fun getUserTodayFoods(): LiveData<List<Food>> {
        return Transformations.map(localDataSource.getUserTodayFoods()) {
            Mapper.foodEntitiesToDomains(it)
        }
    }

    override fun getHistory7Days(): LiveData<List<Food>> {
        return Transformations.map(localDataSource.getHistory7Days()) {
            Mapper.foodEntitiesToDomains(it)
        }
    }

    override fun getHistory30Days(): LiveData<List<Food>> {
        return Transformations.map(localDataSource.getHistory30Days()) {
            Mapper.foodEntitiesToDomains(it)
        }
    }

    override fun getUserTodayTotalCalories(): LiveData<Double> {
        return localDataSource.getUserTodayTotalCalories()
    }

    override suspend fun insertFood(food: Food) {
        val foodEntity = Mapper.foodDomainToEntity(food)
        localDataSource.insertFood(foodEntity)
    }

    override suspend fun detectFoodCalories(foodCaloriesPayload: FoodCaloriesPayload): DetectionResponse {
        return remoteDataSource.detectFoodCaloriesWorth(foodCaloriesPayload)
    }

    override fun detectFoodCaloriesLocal(foodCaloriesPayload: FoodCaloriesPayload): DetectionResponse {
        val result = foodCaloriesPayload.foods!!.map {
            val matchedFood = CaloriesBank.find { food -> food.food == it.name }
            DetectionResult(it.name!!, it.confidence!!, matchedFood!!.calorie.toDouble());
        }
        return DetectionResponse("sukses", result)
    }
}