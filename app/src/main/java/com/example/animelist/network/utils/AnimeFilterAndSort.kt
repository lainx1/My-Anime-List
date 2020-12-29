package com.example.animelist.network.utils

import com.example.animelist.model.Anime
import com.example.animelist.network.enums.AnimeOrder
import com.example.animelist.network.enums.AnimeSort

object AnimeFilterAndSort {
    public  val animeOrder: HashMap<AnimeOrder, String> = hashMapOf(
            AnimeOrder.TITLE to "title",
            AnimeOrder.START_DATE to "start_date",
            AnimeOrder.END_DATE to  "end_date",
            AnimeOrder.SCORE to  "score",
            AnimeOrder.TYPE to "type",
            AnimeOrder.MEMBERS to  "members",
            AnimeOrder.ID to "id",
            AnimeOrder.EPISODES to "episodes",
            AnimeOrder.RATING to "rating"
    )
    val animeSort: HashMap<AnimeSort , String> = hashMapOf(
            AnimeSort.ASC to  "asc",
            AnimeSort.DESC to "desc"
    )
}