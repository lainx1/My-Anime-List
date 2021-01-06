package com.example.animelist.viemodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airbnb.lottie.LottieAnimationView
import com.example.animelist.model.Anime
import com.example.animelist.model.Search
import com.example.animelist.network.AnimeRetrofitClient
import com.example.animelist.network.enums.AnimeOrder
import com.example.animelist.network.enums.AnimeSort
import com.example.animelist.network.model.ApiError
import com.example.animelist.network.utils.AnimeFilterAndSort
import kotlinx.coroutines.*
import timber.log.Timber
import java.lang.Exception

enum class RequestStatus {ERROR, LOADING, DONE}

class AnimeViewModel : ViewModel(){

    //private val viewModelJob = Job();

    //private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private  val _anime = MutableLiveData<Anime>()
    val anime: LiveData<Anime>get() = _anime

    private val _search = MutableLiveData<Search>()
    val search : LiveData<Search> get() = _search

    private val _error = MutableLiveData<ApiError>()
    val error: LiveData<ApiError> get() = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun findAnimeByid(id: Int){

        _loading.value = true


        viewModelScope.launch (Dispatchers.IO){
            val either = AnimeRetrofitClient.retrofitClient.findAnimeByid(id = id)


            withContext(Dispatchers.Main){
                _loading.value = false
                either.fold(
                        {
                            _error.value = it
                        },
                        {
                            _anime.value = it
                        }
                )
            }
        }
    }

    fun searchAnime(q: String = "", page: Int, limit: Int = 20, orderBy: String = AnimeFilterAndSort.animeOrder[AnimeOrder.TITLE]!!, sort: String = AnimeFilterAndSort.animeSort[AnimeSort.ASC]!!){
        _loading.value = true
        Timber.i("Buscando anime")
        viewModelScope.launch (Dispatchers.IO){
            val either = AnimeRetrofitClient.retrofitClient.searchAnime( q = q, page = page, limit = limit, orderBy = orderBy, sort = sort)

            withContext(Dispatchers.Main){
                _loading.value = false
                either.fold(
                        {
                            _error.value = it
                        },
                        {
                            _search.value = it
                        }
                )
            }
        }
    }


}
