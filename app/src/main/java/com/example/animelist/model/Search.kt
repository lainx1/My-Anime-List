package com.example.animelist.model

data class Search(
        val request_hash:String,
        val request_cached: Boolean,
        val request_cache_expiry: Long,
        val results: List<Anime>,
        val last_page: Int


)
