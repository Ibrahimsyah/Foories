package academy.bangkit.capstonk.foories.core.domain.usecase

import academy.bangkit.capstonk.foories.core.domain.model.Calories
import academy.bangkit.capstonk.foories.core.domain.model.Food
import academy.bangkit.capstonk.foories.core.domain.model.User
import academy.bangkit.capstonk.foories.core.domain.repository.IFooriesRepository
import androidx.lifecycle.LiveData

class ScreeningInteractor(private val repository: IFooriesRepository) : ScreeningUseCase {
    override suspend fun getUserCalories(user: User): Calories {
        return repository.getUserCalories(user)
    }

    override fun getUserTodayFoods(): LiveData<List<Food>> {
        return repository.getUserTodayFoods()
    }

    override fun getUserTodayTotalCalories(): LiveData<Double> {
        return repository.getUserTodayTotalCalories()
    }

    override suspend fun insertFood(food: Food) {
        repository.insertFood(food)
    }
}