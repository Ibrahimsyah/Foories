package academy.bangkit.capstonk.foories.core.domain.repository

import academy.bangkit.capstonk.foories.core.domain.model.User

interface IFooriesRepository {
    fun getUserCalories(user: User)
}