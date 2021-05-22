package academy.bangkit.capstonk.foories.core.domain.repository

import academy.bangkit.capstonk.foories.core.domain.model.User

interface IScreeningRepository {
    fun getUserCalories(user: User)
}