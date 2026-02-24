package com.example.artshop.network

import com.example.artshop.models.Artist
import com.example.artshop.models.FrameType
import com.example.artshop.models.Photo
import com.example.artshop.models.PhotoSize
import com.example.artshop.models.Category
import com.example.artshop.models.FrameWidth
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET

interface ApiService {
    @GET("categories") // Assuming you'll have a /categories endpoint (or adjust as needed)
    suspend fun getCategories(): List<Category>

    @GET("artists") // Assuming a /artists endpoint
    suspend fun getArtists(): List<Artist>

    @GET("photos") // Assuming a /photos endpoint
    suspend fun getPhotos(): List<Photo>

    @GET("photos/{photoId}") // Adjust the endpoint path as needed
    suspend fun getPhotoById(@retrofit2.http.Path("photoId") photoId: String): Photo

    @GET("frametypes") // Assuming a /frametypes endpoint
    suspend fun getFrameTypes(): List<FrameType>

    @GET("framewidths") // Assuming a /framewidths endpoint
    suspend fun getFrameWidths(): List<FrameWidth>

    @GET("photosizes") // Assuming a /photosizes endpoint
    suspend fun getPhotoSizes(): List<PhotoSize>


}

object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:3000/" // Replace with your actual base URL

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val okHttpClient: OkHttpClient by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Adjust logging level as needed
        }
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(ApiService::class.java)
    }
}

