package yusuke.saito.randomuser.usecase

import yusuke.saito.randomuser.domain.RandomUser
import yusuke.saito.randomuser.entity.RandomUserEntity
import yusuke.saito.randomuser.repository.RandomUserRepository

class GetRandomUsersUseCaseImpl(
    private val repository: RandomUserRepository
) : GetRandomUsersUseCase {
    override fun getRandomUsers(size: Int): List<RandomUserEntity> =
        repository.getRandomUsers(size).execute().body()?.results?.toEntities() ?: listOf()
}

fun RandomUser.toEntity() = RandomUserEntity(gender, phone)
fun List<RandomUser>.toEntities() = this.map { it.toEntity() }

