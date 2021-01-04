package com.example.animelist.network

import com.example.animelist.model.Anime
import com.example.animelist.model.Search
import com.example.animelist.network.enums.AnimeOrder
import com.example.animelist.network.enums.AnimeSort
import com.example.animelist.network.utils.AnimeFilterAndSort
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeRepository {

    @GET("anime/{id}")
    suspend fun findAnimeByid(@Path("id")id: Int): Anime

    @GET(value = "search/anime")
    suspend fun searchAnime(
            @Query(value = "q") q:String = "",
            @Query(value = "page") page:Int = 1,
            @Query(value = "limit") limit:Int = 20,
            @Query(value = "order_by") orderBy: String = AnimeFilterAndSort.animeOrder[AnimeOrder.TITLE]!!,
            @Query(value = "sort") sort: String = AnimeFilterAndSort.animeSort[AnimeSort.ASC]!!,
    ): Search
}