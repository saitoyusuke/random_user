package yusuke.saito.randomuser

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

class Repository(private val url: String) {

    private val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(
                    MoshiConverterFactory.create(
                            Moshi.Builder()
                                    .add(KotlinJsonAdapterFactory())
                                    .build()
                    )
            ).client(
                    OkHttpClient.Builder()
                            .connectTimeout(120, TimeUnit.SECONDS)
                            .readTimeout(120, TimeUnit.SECONDS)
                            .addInterceptor(HttpLoggingInterceptor().apply {
                                level = HttpLoggingInterceptor.Level.BODY
                            })
                            .build()
            ).build()

    fun getResults(): Response<Results> {
        val service = retrofit.create(ApiInterface::class.java)
        return service.getResults().execute()
    }
}

data class Result(val gender: String, val phone: String)
data class Results(val results: List<Result>)
interface ApiInterface {
    @GET("api/")
    fun getResults(): Call<Results>
}
