package com.example.animelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.animelist.viemodel.AnimeViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val animeViewModel = AnimeViewModel()
        animeViewModel.anime.observe(this, Observer {
            print(it.title)
        })
        animeViewModel.search.observe(this,{
            animeTV.text = it.results.toString()})
        //animeViewModel.findAnimeByid(id = 1)
        animeViewModel.searchAnime(q = "utawarerumono")
    }
}