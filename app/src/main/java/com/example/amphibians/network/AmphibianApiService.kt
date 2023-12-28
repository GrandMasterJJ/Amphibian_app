package com.example.amphibians.network

import com.example.amphibians.data.AmphibianPhotoRepository
import com.example.amphibians.data.NetWorkAmphibianPhotoRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET


/*
private const val Base_Url = "https://android-kotlin-fun-mars-server.appspot.com"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(Base_Url)
    .build()
 */

interface AmphibianApiService {
    @GET("amphibians")
    suspend fun getPhotos(): List<AmphibianPhoto>
    suspend fun getDescription(): List<AmphibianPhoto>
}

/*
object AmphibianApi{
    val retrofitService : AmphibianApiService by lazy {
        retrofit.create(AmphibianApiService::class.java)
    }
}

 */




