package academy.bangkit.capstonk.foories.presentation.detector

import academy.bangkit.capstonk.foories.core.brain.ImageClassifier
import academy.bangkit.capstonk.foories.core.brain.Result
import academy.bangkit.capstonk.foories.core.data.source.remote.entity.FoodCaloriesPayload
import academy.bangkit.capstonk.foories.core.data.source.remote.entity.FoodPayload
import academy.bangkit.capstonk.foories.core.domain.model.DetectionResult
import academy.bangkit.capstonk.foories.core.domain.model.Food
import academy.bangkit.capstonk.foories.core.domain.repository.IFooriesRepository
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DetectorViewModel(
    private val classifier: ImageClassifier,
    private val repository: IFooriesRepository
) : ViewModel() {
    private val loading = MutableLiveData(false)

    fun getLoading() = loading

    fun detectImage(bitmap: Bitmap): LiveData<List<DetectionResult>> {
        val result = MutableLiveData<List<DetectionResult>>()
        viewModelScope.launch {
            loading.postValue(true)
            delay(500)
            val response = classifier.recognizeFlow(bitmap).first()
            val list : MutableList<FoodPayload> = mutableListOf()
            response.forEach {
                list.add(FoodPayload(
                    name = it.title,
                    confidence = it.confidence
                ))
            }

            val payload = FoodCaloriesPayload(foods = list)
            val sendApi = repository.detectFoodCalories(payload)

            if (sendApi.foodsCalories.isNullOrEmpty()) {
                loading.postValue(false)
                result.postValue(mutableListOf())
            } else {
                loading.postValue(false)
                result.postValue(sendApi.foodsCalories)
            }
        }
        return result
    }

    fun saveFood(food: Food) {
        viewModelScope.launch {
            repository.insertFood(food)
        }
    }

    override fun onCleared() {
        super.onCleared()
        classifier.close()
    }
}