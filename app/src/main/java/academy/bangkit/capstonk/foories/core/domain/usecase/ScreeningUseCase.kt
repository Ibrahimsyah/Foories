package academy.bangkit.capstonk.foories.core.domain.usecase

import academy.bangkit.capstonk.foories.core.domain.model.Calories
import academy.bangkit.capstonk.foories.core.domain.model.User
import androidx.lifecycle.LiveData

interface ScreeningUseCase {
    fun getUserCalories(user: User): LiveData<Calories>
}