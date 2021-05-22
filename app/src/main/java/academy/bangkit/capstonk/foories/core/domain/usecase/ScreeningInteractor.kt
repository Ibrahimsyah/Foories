package academy.bangkit.capstonk.foories.core.domain.usecase

import academy.bangkit.capstonk.foories.core.domain.model.User
import academy.bangkit.capstonk.foories.core.domain.repository.IFooriesRepository
import android.util.Log

class ScreeningInteractor(val repository: IFooriesRepository) : ScreeningUseCase {
    override fun getUserCalories(user: User) {
        Log.d("hehe", user.toString())
    }
}