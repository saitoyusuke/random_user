package yusuke.saito.randomuser.usecase

import yusuke.saito.randomuser.domain.RandomUser
import yusuke.saito.randomuser.entity.RandomUserEntity
import yusuke.saito.randomuser.repository.RandomUserRepository

class GetRandomUsersUseCaseImpl(
    private val repository: RandomUserRepository
) : GetRandomUsersUseCase {
    override fun getRandomUsers(page: Int, size: Int): List<RandomUserEntity> =
        repository.getRandomUsers(page, size).execute().body()?.results?.toEntities() ?: listOf()
}

fun RandomUser.toEntity() = RandomUserEntity(gender, phone, picture.thumbnail, email)
fun List<RandomUser>.toEntities() = this.map { it.toEntity() }
