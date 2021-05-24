package academy.bangkit.capstonk.foories.core.data

import academy.bangkit.capstonk.foories.core.Mapper
import academy.bangkit.capstonk.foories.core.data.source.local.LocalDataSource
import academy.bangkit.capstonk.foories.core.data.source.remote.RemoteDataSource
import academy.bangkit.capstonk.foories.core.domain.model.Calories
import academy.bangkit.capstonk.foories.core.domain.model.User
import academy.bangkit.capstonk.foories.core.domain.repository.IFooriesRepository

class FooriesRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : IFooriesRepository {
    override suspend fun getUserCalories(user: User): Calories {
        val result = remoteDataSource.getUserCaloriesNeed(user)
        return Mapper.calorieResponseToDomain(result)
    }
}