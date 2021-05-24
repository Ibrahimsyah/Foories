package academy.bangkit.capstonk.foories.core.domain.usecase

import academy.bangkit.capstonk.foories.core.domain.model.Calories
import academy.bangkit.capstonk.foories.core.domain.model.User

interface ScreeningUseCase {
    suspend fun getUserCalories(user: User): Calories
}