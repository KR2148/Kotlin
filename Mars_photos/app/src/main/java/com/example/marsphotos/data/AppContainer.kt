package com.example.marsphotos.data

import retrofit2.Retrofit
import com.example.marsphotos.network.MarsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

// this file is to store all the dependencies that would be required in the app
// one such dependency is viewmodel creating repository class

//add an abstract property called marsPhotosRepository of type MarsPhotosRepository.
interface AppContainer {
    val marsPhotosRepository : MarsPhotosRepository
}

//DefaultAppContainer that implements the interface AppContainer.
class DefaultAppContainer : AppContainer {

    private  val baseUrl  =
        "https://android-kotlin-fun-mars-server.appspot.com"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }

    // overriding variable present in interface
    override val marsPhotosRepository: MarsPhotosRepository by lazy {
        NetworkMarsPhotosRepository(retrofitService)
    }

}