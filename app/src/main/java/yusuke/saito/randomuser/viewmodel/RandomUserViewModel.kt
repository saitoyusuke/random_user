package yusuke.saito.randomuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import yusuke.saito.randomuser.usecase.GetRandomUsersUseCase

class RandomUserViewModel(private val useCase: GetRandomUsersUseCase) : ViewModel() {

    private val _results = MutableLiveData("")
    val results: LiveData<String> = _results
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun getResults() {
        val job = viewModelScope.launch(context = Dispatchers.IO, start = CoroutineStart.LAZY) {
            val users = useCase.getRandomUsers(30)
            _results.postValue(users[0].gender + " : " + users[0].phone)
            _isLoading.postValue(false)
        }
        _isLoading.postValue(true)
        job.start()
    }
}
