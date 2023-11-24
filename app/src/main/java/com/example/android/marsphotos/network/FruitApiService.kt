
package com.example.android.marsphotos.network

import com.example.android.marsphotos.network.model.Fruit
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://www.fruityvice.com/api/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY // Aquí puedes ajustar el nivel de log deseado
            })
            .build()
    )
    .build()

interface FruitApiService {
    @GET("fruit/all")
    suspend fun getFruits(): List<Fruit>
}

object FruitApi {
    val retrofitService: FruitApiService by lazy { retrofit.create(FruitApiService::class.java) }
}
