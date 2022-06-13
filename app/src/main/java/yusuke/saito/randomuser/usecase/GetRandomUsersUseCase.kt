package yusuke.saito.randomuser.usecase

import yusuke.saito.randomuser.repository.RandomUser

interface GetRandomUsersUseCase {
    fun getRandomUsers(size: Int): List<RandomUser>
}
