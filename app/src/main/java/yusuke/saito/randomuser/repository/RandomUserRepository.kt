package yusuke.saito.randomuser.repository

import retrofit2.Call

class RandomUserRepositoryImpl(private val url: String): Repository(url), RandomUserRepository {
    override fun getRandomUsers(size: Int): Call<RandomUsers> =
        retrofit.create(RandomUserRepository::class.java).getRandomUsers(size)
}
