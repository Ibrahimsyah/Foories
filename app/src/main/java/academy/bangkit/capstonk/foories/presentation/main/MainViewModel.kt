package academy.bangkit.capstonk.foories.presentation.main

import academy.bangkit.capstonk.foories.core.domain.model.Food
import academy.bangkit.capstonk.foories.core.domain.usecase.ScreeningUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val useCase: ScreeningUseCase) : ViewModel() {
    fun getUserTodayFoods() = useCase.getUserTodayFoods()


    fun getUserTodayTotalCalories() = useCase.getUserTodayTotalCalories()

    fun insertFood(food: Food) {
        viewModelScope.launch {
            useCase.insertFood(food)
        }
    }
}