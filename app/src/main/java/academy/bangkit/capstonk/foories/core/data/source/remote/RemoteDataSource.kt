package academy.bangkit.capstonk.foories.core.data.source.remote

import academy.bangkit.capstonk.foories.core.data.source.remote.network.FooriesApi
import academy.bangkit.capstonk.foories.core.domain.model.User

class RemoteDataSource(private val fooriesApi: FooriesApi) {
    suspend fun getUserCaloriesNeed(user: User) = fooriesApi.getUserCalorie(user)
}