package academy.bangkit.capstonk.foories.core.data

import academy.bangkit.capstonk.foories.core.domain.model.Calories
import academy.bangkit.capstonk.foories.core.domain.model.User
import academy.bangkit.capstonk.foories.core.domain.repository.IFooriesRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FooriesRepository : IFooriesRepository {
    override fun getUserCalories(user: User): LiveData<Calories> {
        return MutableLiveData(Calories(4000.0))
    }
}