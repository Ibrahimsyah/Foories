package academy.bangkit.capstonk.foories.core.domain.usecase

import academy.bangkit.capstonk.foories.core.brain.Result
import academy.bangkit.capstonk.foories.core.data.source.remote.entity.FoodCaloriesPayload
import academy.bangkit.capstonk.foories.core.data.source.remote.response.DetectionResponse
import academy.bangkit.capstonk.foories.core.domain.model.Food
import android.graphics.Bitmap
import kotlinx.coroutines.flow.Flow

interface DetectorUseCase {
    fun classifyImage(bitmap: Bitmap): Flow<List<Result>>

    suspend fun detectFoodsCalorie(payload: FoodCaloriesPayload): DetectionResponse

    suspend fun insertFood(food: Food)

    fun closeClassifier()
}