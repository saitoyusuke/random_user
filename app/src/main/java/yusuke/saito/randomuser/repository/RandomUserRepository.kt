package yusuke.saito.randomuser.repository

import retrofit2.Call

class RandomUserRepository(private val url: String): Repository(url), RandomUserUseCase {
    override fun getResults(size: Int): Call<Results> {
        val service = retrofit.create(RandomUserUseCase::class.java)
        return service.getResults(size)
    }
}
