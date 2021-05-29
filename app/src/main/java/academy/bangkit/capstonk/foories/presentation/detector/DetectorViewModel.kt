package academy.bangkit.capstonk.foories.presentation.detector

import academy.bangkit.capstonk.foories.core.brain.ImageClassifier
import academy.bangkit.capstonk.foories.core.brain.Result
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

    fun detectImage(bitmap: Bitmap): LiveData<List<Result>> {
        val result = MutableLiveData<List<Result>>()
        viewModelScope.launch {
            loading.postValue(true)
            delay(500)
            val response = classifier.recognizeFlow(bitmap).first()
            loading.postValue(false)
            result.postValue(response)
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