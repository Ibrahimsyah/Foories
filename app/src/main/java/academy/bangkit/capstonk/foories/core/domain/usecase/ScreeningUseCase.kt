package academy.bangkit.capstonk.foories.core.domain.usecase

import academy.bangkit.capstonk.foories.core.domain.model.User

interface ScreeningUseCase {
    fun getUserCalories(user: User)
}