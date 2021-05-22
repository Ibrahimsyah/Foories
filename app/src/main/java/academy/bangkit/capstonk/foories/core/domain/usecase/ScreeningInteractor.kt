package academy.bangkit.capstonk.foories.core.domain.usecase

import academy.bangkit.capstonk.foories.core.domain.model.User
import academy.bangkit.capstonk.foories.core.domain.repository.IScreeningRepository

class ScreeningInteractor(val repository: IScreeningRepository) : ScreeningUseCase {
    override fun getUserCalories(user: User) {
        TODO("Not yet implemented")
    }
}