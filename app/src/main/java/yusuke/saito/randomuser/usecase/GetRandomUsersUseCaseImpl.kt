package yusuke.saito.randomuser.usecase

import yusuke.saito.randomuser.repository.RandomUserEntity
import yusuke.saito.randomuser.repository.RandomUserRepository
import yusuke.saito.randomuser.repository.toEntities

class GetRandomUsersUseCaseImpl(
    private val repository: RandomUserRepository
) : GetRandomUsersUseCase {
    override fun getRandomUsers(size: Int): List<RandomUserEntity> =
        repository.getRandomUsers(size).execute().body()?.results?.toEntities() ?: listOf()
}