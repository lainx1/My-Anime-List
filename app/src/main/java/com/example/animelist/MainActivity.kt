package com.example.animelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.animelist.model.Search
import com.example.animelist.viemodel.AnimeViewModel
import com.example.animelist.view.AnimeAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NestedScrollView.OnScrollChangeListener {

    private val LIMIT = 20
    private var animeViewModel: AnimeViewModel? = null
    private var PAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        animeViewModel = AnimeViewModel()
        val animeAdapter = AnimeAdapter(mutableListOf())

        with(animeListRv) {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MainActivity, 2, GridLayoutManager.VERTICAL, false)
            adapter = animeAdapter
        }

        animeViewModel!!.search.observe(this, {
            animeAdapter.addAnimes(it.results)
            ///
        })

        animeViewModel!!.searchAnime(q = "utawareumono" , page = PAGE, limit = LIMIT)
    }

    override fun onStart() {
        super.onStart()
        scrollView.setOnScrollChangeListener(this)
    }

    override fun onScrollChange(v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
        if (scrollY == v!!.getChildAt(0).measuredHeight - v.measuredHeight){
            PAGE++
            animeViewModel!!.searchAnime(q = "utawareumono" , page = PAGE, limit = LIMIT)

        }

    }
}