package yusuke.saito.randomuser.usecase

import yusuke.saito.randomuser.entity.RandomUserEntity

interface GetRandomUsersUseCase {
    fun getRandomUsers(page: Int, size: Int): List<RandomUserEntity>
}
