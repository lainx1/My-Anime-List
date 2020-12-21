package com.example.animelist.network

import com.example.animelist.model.Anime
import com.example.animelist.model.Search
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeRepository {

    @GET("anime/{id}")
    suspend fun findAnimeByid(@Path("id")id: Int): Anime

    @GET(value = "search/anime")
    suspend fun searchAnime(@Query(value = "q") q:String): Search
}