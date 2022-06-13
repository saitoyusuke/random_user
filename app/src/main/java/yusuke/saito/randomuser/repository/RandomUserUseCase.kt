package yusuke.saito.randomuser.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserUseCase {
    @GET("api/")
    fun getResults(@Query("results") size: Int): Call<Results>
}

data class Result(val gender: String, val phone: String)
data class Results(val results: List<Result>)

