package yusuke.saito.randomuser.usecase

import yusuke.saito.randomuser.repository.RandomUsers

interface GetRandomUsersUseCase {
    fun getRandomUsers(size: Int): RandomUsers
}
