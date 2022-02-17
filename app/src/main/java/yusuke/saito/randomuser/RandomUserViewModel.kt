package yusuke.saito.randomuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RandomUserViewModel(private val repository: Repository) : ViewModel() {

    private val _results = MutableLiveData("")
    val results: LiveData<String> = _results

    fun getResults() {
        viewModelScope.launch { withContext(Dispatchers.IO) {
            val response = repository.getResults().body()
            response?.let {
                _results.postValue(it.results[0].gender + " : " + it.results[0].phone)
            }
        } }.start()
    }
}
