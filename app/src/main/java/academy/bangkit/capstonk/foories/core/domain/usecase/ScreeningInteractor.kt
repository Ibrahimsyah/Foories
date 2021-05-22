package academy.bangkit.capstonk.foories.core.domain.usecase

import academy.bangkit.capstonk.foories.core.domain.model.Calories
import academy.bangkit.capstonk.foories.core.domain.model.User
import academy.bangkit.capstonk.foories.core.domain.repository.IFooriesRepository
import androidx.lifecycle.LiveData

class ScreeningInteractor(private val repository: IFooriesRepository) : ScreeningUseCase {
    override fun getUserCalories(user: User): LiveData<Calories> {
        return repository.getUserCalories(user)
    }
}