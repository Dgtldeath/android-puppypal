package com.adamgumm.puppypal

import retrofit2.http.GET
import retrofit2.Response

interface ApiService {
    @GET("puppies.php")
    suspend fun getPuppies(): Response<List<Puppy>>

    companion object {
        fun create(): ApiService {
            return retrofit2.Retrofit.Builder()
                .baseUrl("https://your-third-party-api-domain.com/")  // Replace with your actual URL!
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}
