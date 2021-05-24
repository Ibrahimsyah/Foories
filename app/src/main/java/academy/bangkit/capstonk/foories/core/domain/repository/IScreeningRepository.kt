package academy.bangkit.capstonk.foories.core.domain.repository

import academy.bangkit.capstonk.foories.core.domain.model.Calories
import academy.bangkit.capstonk.foories.core.domain.model.User

interface IFooriesRepository {
    suspend fun getUserCalories(user: User): Calories
}