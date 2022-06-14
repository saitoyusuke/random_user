package yusuke.saito.randomuser.repository

import retrofit2.Call
import yusuke.saito.randomuser.domain.RandomUsers

class RandomUserRepositoryImpl(private val url: String): Repository(url), RandomUserRepository {
    override fun getRandomUsers(page: Int, size: Int): Call<RandomUsers> =
        retrofit.create(RandomUserRepository::class.java).getRandomUsers(page, size)
}
