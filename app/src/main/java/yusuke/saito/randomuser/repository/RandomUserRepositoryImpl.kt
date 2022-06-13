package yusuke.saito.randomuser.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import yusuke.saito.randomuser.domain.RandomUsers

interface RandomUserRepository {
    @GET("api/")
    fun getRandomUsers(@Query("results") size: Int): Call<RandomUsers>
}
