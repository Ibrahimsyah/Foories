package academy.bangkit.capstonk.foories.core.data.source.remote.network

import academy.bangkit.capstonk.foories.core.data.source.remote.response.CalorieResponse
import academy.bangkit.capstonk.foories.core.domain.model.User
import retrofit2.http.Body
import retrofit2.http.POST


interface FooriesApi {
    @POST("calorie")
    suspend fun getUserCalorie(@Body user: User): CalorieResponse
}