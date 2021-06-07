package academy.bangkit.capstonk.foories.presentation.home

import academy.bangkit.capstonk.foories.core.domain.model.Food
import academy.bangkit.capstonk.foories.core.domain.usecase.HomeUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel(private val useCase: HomeUseCase) : ViewModel() {
    fun getUserTodayFoods() = useCase.getUserTodayFoods()

    fun getHistory7Days() = useCase.getHistory7Days()

    fun getHistory30Days() = useCase.getHistory30Days()

    fun getUserTodayTotalCalories() = useCase.getUserTodayTotalCalories()

    fun insertFood(food: Food) {
        viewModelScope.launch {
            useCase.insertFood(food)
        }
    }
}