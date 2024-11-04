package com.example.harry_details.network
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.http.Path
import com.example.harry_details.models.Character

interface HarryPotterApiService {
    @GET("characters")
    suspend fun getCharacters(): List<Character>
    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: String): List<Character>
}

object HarryPoterInstance {
    private const val BASE_URL: String = "https://hp-api.onrender.com/api/"

    val api: HarryPotterApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HarryPotterApiService::class.java)
    }
}

