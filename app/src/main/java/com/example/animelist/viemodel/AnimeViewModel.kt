package com.example.animelist.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animelist.model.Anime
import com.example.animelist.model.Search
import com.example.animelist.network.AnimeRetrofitClient
import com.example.animelist.network.enums.AnimeOrder
import com.example.animelist.network.enums.AnimeSort
import com.example.animelist.network.utils.AnimeFilterAndSort
import kotlinx.coroutines.*
import java.lang.Exception
import java.nio.ByteOrder

enum class RequestStatus {ERROR, LOADING, DONE}

class AnimeViewModel : ViewModel(){

    //private val viewModelJob = Job();

    //private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private  val _anime = MutableLiveData<Anime>()
    val anime: LiveData<Anime>get() = _anime

    private val _search = MutableLiveData<Search>()
    val search : LiveData<Search> get() = _search

    fun findAnimeByid(id: Int){
        viewModelScope.launch (Dispatchers.IO){
            val anime = AnimeRetrofitClient.retrofitClient.findAnimeByid(id = id)
            anime?.let {
                withContext(Dispatchers.Main){
                    _anime.value = it
                }
            }
        }
        //coroutineScope.launch {
//            val animeDeferret = AnimeRetrofitClient.retrofitClient.findAnimeByid(id = id)
//            try {
//                val anime = animeDeferret.await()
//                _anime.value = anime
//
//            }catch (e: Exception){}
        }
    fun searchAnime(q:  String = "", page: Int, limit: Int = 20, order_by: String = AnimeFilterAndSort.animeOrder[AnimeOrder.TITLE]!!, sort: String = AnimeFilterAndSort.animeSort[AnimeSort.ASC]!!){
        viewModelScope.launch (Dispatchers.IO){
            val search = AnimeRetrofitClient.retrofitClient.searchAnime( q= q, page=page, limit = limit,order_by = order_by,sort = sort)
            search?.let {
                withContext(Dispatchers.Main){
                    _search.value = it
                }

            }
        }
    }

//    override fun onCleared() {
//        super.onCleared()
//        viewModelJob.cancel()
//    }
}
