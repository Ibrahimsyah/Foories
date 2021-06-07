package academy.bangkit.capstonk.foories.core.data

import academy.bangkit.capstonk.foories.core.data.source.local.LocalDataSource
import academy.bangkit.capstonk.foories.core.data.source.remote.RemoteDataSource
import academy.bangkit.capstonk.foories.core.data.source.remote.entity.FoodCaloriesPayload
import academy.bangkit.capstonk.foories.core.data.source.remote.response.DetectionResponse
import academy.bangkit.capstonk.foories.core.domain.model.Calories
import academy.bangkit.capstonk.foories.core.domain.model.Food
import academy.bangkit.capstonk.foories.core.domain.model.User
import academy.bangkit.capstonk.foories.core.domain.repository.IFooriesRepository
import academy.bangkit.capstonk.foories.core.util.Mapper
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
}