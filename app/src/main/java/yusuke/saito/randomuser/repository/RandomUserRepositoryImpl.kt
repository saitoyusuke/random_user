package yusuke.saito.randomuser.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserRepository {
    @GET("api/")
    fun getRandomUsers(@Query("results") size: Int): Call<RandomUsers>
}

data class RandomUser(val gender: String, val phone: String)
data class RandomUsers(val results: List<RandomUser>)

data class RandomUserEntity(val gender: String, val phone: String)

fun RandomUser.toEntity() = RandomUserEntity(gender, phone)
fun List<RandomUser>.toEntities() = this.map { it.toEntity() }

