package academy.bangkit.capstonk.foories.core.domain.repository

import academy.bangkit.capstonk.foories.core.domain.model.Calories
import academy.bangkit.capstonk.foories.core.domain.model.User
import androidx.lifecycle.LiveData

interface IFooriesRepository {
    fun getUserCalories(user: User): LiveData<Calories>
}