package academy.bangkit.capstonk.foories.core.data.source.remote.network

import academy.bangkit.capstonk.foories.core.data.source.remote.entity.FoodCaloriesPayload
import academy.bangkit.capstonk.foories.core.data.source.remote.response.CalorieResponse
import academy.bangkit.capstonk.foories.core.data.source.remote.response.DetectionResponse
import academy.bangkit.capstonk.foories.core.domain.model.User
import retrofit2.http.Body
import retrofit2.http.POST


interface FooriesApi {
    @POST("calorie")
    suspend fun getUserCalorie(@Body user: User): CalorieResponse

    @POST("calories")
    suspend fun detectFoodCalories(@Body foodCaloriesPayload: FoodCaloriesPayload): DetectionResponse
}