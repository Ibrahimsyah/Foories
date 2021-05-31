package academy.bangkit.capstonk.foories.presentation.detector

import academy.bangkit.capstonk.foories.core.brain.ImageClassifier
import academy.bangkit.capstonk.foories.core.data.source.remote.entity.FoodCaloriesPayload
import academy.bangkit.capstonk.foories.core.data.source.remote.entity.FoodPayload
import academy.bangkit.capstonk.foories.core.domain.model.DetectionResult
import academy.bangkit.capstonk.foories.core.domain.model.Food
import academy.bangkit.capstonk.foories.core.domain.repository.IFooriesRepository
import academy.bangkit.capstonk.foories.core.util.Mapper
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
            val response = classifier.recognizeFlow(bitmap).first()
            val list : MutableList<FoodPayload> = mutableListOf()
            response.forEach {
                list.add(FoodPayload(
                    name = it.title,
                    confidence = it.confidence?.toDouble()
                ))
            }

            val payload = FoodCaloriesPayload(list)
            val sendApi = repository.detectFoodCalories(payload)
            if (sendApi.foodsCalories.isNullOrEmpty()) {
                result.postValue(mutableListOf())
            } else {
                Log.d("hehe", "${sendApi.foodsCalories}")
                val formattedResult = Mapper.reformatDetectionResult(sendApi.foodsCalories)
                result.postValue(formattedResult)
            }
            loading.postValue(false)
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