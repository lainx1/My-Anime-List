package com.example.animelist.network

import com.example.animelist.network.enums.StatusCode
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object AnimeRetrofitClient {

    private val BASE_URL = "https://api.jikan.moe/v3/"
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val loggingInterce1ptor = Interceptor { chain ->
        val request = chain.request()
        val response = chain.proceed(request)

        if(response.isSuccessful){

        }

        Response
                .Builder()
                //.message(response)
                .build()
    }

    private val okHttpInterceptorClient = OkHttpClient.Builder()
            //.addInterceptor(loggingInterce1ptor)
            .build()


    private val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
//        .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL)
            .client(okHttpInterceptorClient)
            .build()

    val retrofitClient : AnimeRepository by lazy {
        retrofit.create(AnimeRepository::class.java)
    }
}