package academy.bangkit.capstonk.foories.core.domain.usecase

import academy.bangkit.capstonk.foories.core.brain.ImageClassifier
import academy.bangkit.capstonk.foories.core.brain.Result
import academy.bangkit.capstonk.foories.core.data.source.remote.entity.FoodCaloriesPayload
import academy.bangkit.capstonk.foories.core.data.source.remote.response.DetectionResponse
import academy.bangkit.capstonk.foories.core.domain.model.Food
import academy.bangkit.capstonk.foories.core.domain.repository.IFooriesRepository
import android.graphics.Bitmap
import kotlinx.coroutines.flow.Flow

class DetectorInteractor(
    private val repository: IFooriesRepository,
    private val classifier: ImageClassifier
) : DetectorUseCase {
    override fun classifyImage(bitmap: Bitmap): Flow<List<Result>> {
        return classifier.recognizeFlow(bitmap)
    }

    override suspend fun detectFoodsCalorie(payload: FoodCaloriesPayload): DetectionResponse {
        return repository.detectFoodCalories(payload)
    }

    override suspend fun insertFood(food: Food) {
        repository.insertFood(food)
    }

    override fun closeClassifier() {
        classifier.close()
    }
}