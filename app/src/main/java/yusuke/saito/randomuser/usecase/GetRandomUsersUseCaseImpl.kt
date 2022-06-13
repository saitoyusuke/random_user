package yusuke.saito.randomuser.usecase

import yusuke.saito.randomuser.repository.RandomUserRepository
import yusuke.saito.randomuser.repository.RandomUsers

class GetRandomUsersUseCaseImpl(private val repository: RandomUserRepository) : GetRandomUsersUseCase {

    override fun getRandomUsers(size: Int): RandomUsers =
        repository.getRandomUsers(size).execute().body() ?: RandomUsers(listOf())
}