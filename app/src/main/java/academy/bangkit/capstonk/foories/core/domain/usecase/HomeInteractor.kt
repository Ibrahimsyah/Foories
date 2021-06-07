package academy.bangkit.capstonk.foories.core.domain.usecase

import academy.bangkit.capstonk.foories.core.domain.model.Food
import academy.bangkit.capstonk.foories.core.domain.repository.IFooriesRepository
import androidx.lifecycle.LiveData

class HomeInteractor(private val repository: IFooriesRepository) : HomeUseCase {
    override fun getUserTodayTotalCalories(): LiveData<Double> {
        return repository.getUserTodayTotalCalories()
    }

    override fun getUserTodayFoods(): LiveData<List<Food>> {
        return repository.getUserTodayFoods()
    }

    override fun getHistory7Days(): LiveData<List<Food>> {
        return repository.getHistory7Days()
    }

    override fun getHistory30Days(): LiveData<List<Food>> {
        return repository.getHistory30Days()
    }

    override suspend fun insertFood(food: Food) {
        repository.insertFood(food)
    }
}