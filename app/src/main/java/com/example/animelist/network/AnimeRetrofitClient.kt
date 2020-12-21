package com.example.animelist.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object AnimeRetrofitClient {

    private val BASE_URL = "https://api.jikan.moe/v3/"
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
//        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

    val retrofitClient : AnimeRepository by lazy {
        retrofit.create(AnimeRepository::class.java) }
}