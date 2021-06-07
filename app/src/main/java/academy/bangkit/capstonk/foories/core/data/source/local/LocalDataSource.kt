package academy.bangkit.capstonk.foories.core.data.source.local

import academy.bangkit.capstonk.foories.core.data.source.local.database.FooriesDao
import academy.bangkit.capstonk.foories.core.data.source.local.entity.FoodEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSource(private val fooriesDao: FooriesDao) {
    fun getUserTodayFoods() = fooriesDao.getTodayFoods()

    suspend fun insertFood(foodEntity: FoodEntity) {
        withContext(Dispatchers.IO) {
            fooriesDao.insertFood(foodEntity)
        }
    }

    fun getUserTodayTotalCalories() = fooriesDao.getTodayTotalCalories()

    fun getHistory7Days() = fooriesDao.getAWeekFood()

    fun getHistory30Days() = fooriesDao.getAMonthFood()
}