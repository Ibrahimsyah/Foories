package academy.bangkit.capstonk.foories.presentation.detector

import academy.bangkit.capstonk.foories.core.data.source.remote.entity.FoodCaloriesPayload
import academy.bangkit.capstonk.foories.core.data.source.remote.entity.FoodPayload
import academy.bangkit.capstonk.foories.core.domain.model.DetectionResult
import academy.bangkit.capstonk.foories.core.domain.model.Food
import academy.bangkit.capstonk.foories.core.domain.usecase.DetectorUseCase
import academy.bangkit.capstonk.foories.core.util.Mapper
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DetectorViewModel(
    private val detectorInteractor: DetectorUseCase
) : ViewModel() {
    private val loading = MutableLiveData(false)

    fun getLoading() = loading

    fun detectImage(bitmap: Bitmap): LiveData<List<DetectionResult>> {
        val result = MutableLiveData<List<DetectionResult>>()
        viewModelScope.launch {
            loading.postValue(true)
            val response = detectorInteractor.classifyImage(bitmap).first()
            val list : MutableList<FoodPayload> = mutableListOf()
            response.forEach {
                list.add(FoodPayload(
                    name = it.title,
                    confidence = it.confidence?.toDouble()
                ))
            }

            val payload = FoodCaloriesPayload(list)
            val sendApi = detectorInteractor.detectFoodsCalorie(payload)
            if (sendApi.foodsCalories.isNullOrEmpty()) {
                result.postValue(mutableListOf())
            } else {
                val formattedResult = Mapper.reformatDetectionResult(sendApi.foodsCalories)
                result.postValue(formattedResult)
            }
            loading.postValue(false)
        }
        return result
    }

    fun saveFood(food: Food) {
        viewModelScope.launch {
            detectorInteractor.insertFood(food)
        }
    }

    override fun onCleared() {
        super.onCleared()
        detectorInteractor.closeClassifier()
    }
}