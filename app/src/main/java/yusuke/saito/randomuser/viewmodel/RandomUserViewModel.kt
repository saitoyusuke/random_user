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

    fun getResults() {
        val job = viewModelScope.launch(context = Dispatchers.IO, start = CoroutineStart.LAZY) {
            val users = useCase.getRandomUsers(30).results
            _results.postValue(users[0].gender + " : " + users[0].phone)
        }
        job.start()
    }
}
