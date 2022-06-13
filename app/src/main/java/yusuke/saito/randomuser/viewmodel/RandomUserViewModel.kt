package yusuke.saito.randomuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import yusuke.saito.randomuser.repository.RandomUserRepository

class RandomUserViewModel(private val repository: RandomUserRepository) : ViewModel() {

    private val _results = MutableLiveData("")
    val results: LiveData<String> = _results

    fun getResults() {
        val job = viewModelScope.launch(context = Dispatchers.IO, start = CoroutineStart.LAZY) {
            val results = repository.getRandomUsers(30).execute().body()?.results
            results?.let {
                _results.postValue(it[0].gender + " : " + it[0].phone)
            }
        }
        job.start()
    }
}
