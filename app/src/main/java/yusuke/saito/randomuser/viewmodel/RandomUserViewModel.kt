package yusuke.saito.randomuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import yusuke.saito.randomuser.repository.RandomUserRepository

class RandomUserViewModel(private val repository: RandomUserRepository) : ViewModel() {

    private val _results = MutableLiveData("")
    val results: LiveData<String> = _results

    fun getResults() {
        viewModelScope.launch { withContext(Dispatchers.IO) {
            val results = repository.getResults(30).execute().body()?.results
            results?.let {
                _results.postValue(it[0].gender + " : " + it[0].phone)
            }
        } }.start()
    }
}
