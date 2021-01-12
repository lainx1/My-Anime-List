package com.example.animelist.model

import java.io.Serializable

data class Anime(
    val mal_id: Int,
    val image_url: String,
    val airing: Boolean,
    val episodes: Int,
    val rating: String?,
    val score: Double?,
    val synopsis: String,
    val genres: List<Genre>?,
    val title: String,

    ): Serializable




