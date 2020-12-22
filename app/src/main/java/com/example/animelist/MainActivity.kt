package com.example.animelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.animelist.model.Search
import com.example.animelist.viemodel.AnimeViewModel
import com.example.animelist.view.AnimeAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val animeViewModel = AnimeViewModel()

        animeViewModel.search.observe(this, {
            ///

            with(animeListRv){
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(this@MainActivity, 2, GridLayoutManager.VERTICAL, false)
                adapter =AnimeAdapter(it.results)
            }
        })

        animeViewModel.searchAnime(q="utawarerumono")

    }

}