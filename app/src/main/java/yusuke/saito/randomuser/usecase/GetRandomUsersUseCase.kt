package yusuke.saito.randomuser.usecase

import yusuke.saito.randomuser.repository.RandomUserEntity

interface GetRandomUsersUseCase {
    fun getRandomUsers(size: Int): List<RandomUserEntity>
}
