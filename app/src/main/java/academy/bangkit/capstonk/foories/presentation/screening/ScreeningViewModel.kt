package academy.bangkit.capstonk.foories.presentation.screening

import academy.bangkit.capstonk.foories.core.domain.model.User
import academy.bangkit.capstonk.foories.core.domain.usecase.ScreeningUseCase
import androidx.lifecycle.ViewModel

class ScreeningViewModel(private val useCase: ScreeningUseCase) : ViewModel() {
    fun getUserCalories(user: User) {
        useCase.getUserCalories(user)
    }
}