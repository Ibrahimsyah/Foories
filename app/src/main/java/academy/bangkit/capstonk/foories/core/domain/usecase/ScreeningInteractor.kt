package academy.bangkit.capstonk.foories.core.domain.usecase

import academy.bangkit.capstonk.foories.core.domain.model.Calories
import academy.bangkit.capstonk.foories.core.domain.model.User
import academy.bangkit.capstonk.foories.core.domain.repository.IFooriesRepository

class ScreeningInteractor(private val repository: IFooriesRepository) : ScreeningUseCase {
    override suspend fun getUserCalories(user: User): Calories {
        return repository.getUserCalories(user)
    }
}