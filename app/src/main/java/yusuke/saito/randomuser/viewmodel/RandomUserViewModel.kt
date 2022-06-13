package yusuke.saito.randomuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import yusuke.saito.randomuser.entity.RandomUserEntity
import yusuke.saito.randomuser.ext.combine
import yusuke.saito.randomuser.usecase.GetRandomUsersUseCase

class RandomUserViewModel(private val useCase: GetRandomUsersUseCase) : ViewModel() {

    private val _users = MutableLiveData<List<RandomUserEntity>>(listOf())
    private val _isLoading = MutableLiveData(false)
    val uiModel: LiveData<RandomUsersUiModel> by lazy {
        combine(
            RandomUsersUiModel(),
            _isLoading,
            _users
        ) { _, isLoading, users ->
            RandomUsersUiModel(isLoading, users)
        }
    }

    fun getResults() {
        val job = viewModelScope.launch(context = Dispatchers.IO, start = CoroutineStart.LAZY) {
            _users.postValue(useCase.getRandomUsers(30))
            _isLoading.postValue(false)
        }
        _isLoading.postValue(true)
        job.start()
    }
}
