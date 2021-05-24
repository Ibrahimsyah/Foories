package academy.bangkit.capstonk.foories.core.domain.usecase

import academy.bangkit.capstonk.foories.core.domain.model.Calories
import academy.bangkit.capstonk.foories.core.domain.model.Food
import academy.bangkit.capstonk.foories.core.domain.model.User
import androidx.lifecycle.LiveData

interface ScreeningUseCase {
    suspend fun getUserCalories(user: User): Calories

    fun getUserTodayFoods(): LiveData<List<Food>>

    fun getUserTodayTotalCalories(): LiveData<Double>

    suspend fun insertFood(food: Food)
}