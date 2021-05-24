package academy.bangkit.capstonk.foories.presentation.screening

import academy.bangkit.capstonk.foories.core.domain.model.Calories
import academy.bangkit.capstonk.foories.core.domain.model.User
import academy.bangkit.capstonk.foories.core.domain.usecase.ScreeningUseCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ScreeningViewModel(private val useCase: ScreeningUseCase) : ViewModel() {
    fun getUserCalories(user: User): LiveData<Calories> {
        val result = MutableLiveData<Calories>()
        viewModelScope.launch {
            val data = useCase.getUserCalories(user)
            result.postValue(data)
        }
        return result
    }
}