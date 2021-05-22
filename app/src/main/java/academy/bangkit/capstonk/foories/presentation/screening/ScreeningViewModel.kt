package academy.bangkit.capstonk.foories.presentation.screening

import academy.bangkit.capstonk.foories.core.domain.model.Calories
import academy.bangkit.capstonk.foories.core.domain.model.User
import academy.bangkit.capstonk.foories.core.domain.usecase.ScreeningUseCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class ScreeningViewModel(private val useCase: ScreeningUseCase) : ViewModel() {
    fun getUserCalories(user: User): LiveData<Calories> {
        return useCase.getUserCalories(user)
    }
}