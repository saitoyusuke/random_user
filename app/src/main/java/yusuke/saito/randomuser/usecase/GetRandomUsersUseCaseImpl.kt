package yusuke.saito.randomuser.usecase

import yusuke.saito.randomuser.repository.RandomUser
import yusuke.saito.randomuser.repository.RandomUserRepository

class GetRandomUsersUseCaseImpl(private val repository: RandomUserRepository) : GetRandomUsersUseCase {

    override fun getRandomUsers(size: Int): List<RandomUser> =
        repository.getRandomUsers(size).execute().body()?.results ?: listOf()
}