package academy.bangkit.capstonk.foories.core.domain.usecase

import academy.bangkit.capstonk.foories.core.domain.model.Food
import androidx.lifecycle.LiveData

interface HomeUseCase {
    fun getUserTodayTotalCalories(): LiveData<Double>
    fun getUserTodayFoods(): LiveData<List<Food>>
    fun getHistory7Days(): LiveData<List<Food>>
    fun getHistory30Days(): LiveData<List<Food>>
    suspend fun insertFood(food: Food)
}