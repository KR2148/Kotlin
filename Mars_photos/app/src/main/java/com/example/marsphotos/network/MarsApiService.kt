
package com.example.marsphotos.network

import com.example.marsphotos.model.MarsPhoto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
//  base URL for the web service
    private const val BASE_URL =
        "https://android-kotlin-fun-mars-server.appspot.com"

    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */

// Retrofit builder to build and create a Retrofit object.
//Retrofit needs the base URI for the web service and a converter factory to build a web services API.
//converter tells Retrofit what to do with the data it gets back from the web service.
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    /**
     * Retrofit service object for creating api calls
     */
    // MarsApiService that defines how Retrofit talks to the web server using HTTP requests.

    interface MarsApiService {
        @GET("photos")
        suspend fun getPhotos(): List<MarsPhoto>
    }

    /**
     * A public Api object that exposes the lazy-initialized Retrofit service
     */
    //The app needs only one instance of the Retrofit API service, so you expose the service to the rest of the app using object declaration.

    object MarsApi {
        // lazily initialized retrofit object property named retrofitService of the type MarsApiService
//        val retrofitService: MarsApiService by lazy {
//            retrofit.create(MarsApiService::class.java)
        }
        // ABOVE OBJECT
//The Retrofit setup is done! Each time your app calls MarsApi.retrofitService, the caller accesses the same singleton Retrofit object that implements MarsApiService,
// which is created on the first access.

