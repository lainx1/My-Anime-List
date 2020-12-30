package com.example.animelist.view.activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.animelist.R
import com.example.animelist.viemodel.AnimeViewModel
import com.example.animelist.view.AnimeAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NestedScrollView.OnScrollChangeListener {


    private var animeViewModel: AnimeViewModel? = null
    private var PAGE = 1

    /**********************************************************************************************
     *  ACTIVITY METODS
    **********************************************************************************************/

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

        animeViewModel!!.searchAnime( page = PAGE)
    }

    override fun onStart() {
        super.onStart()
        scrollView.setOnScrollChangeListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.icon_menu,menu)

        menu!!.findItem(R.id.searchIcon).setOnMenuItemClickListener {
            val intent = Intent(this@MainActivity, SearchActivity::class.java)
            startActivity(intent)
            return@setOnMenuItemClickListener true
        }



        return true
    }


    /**********************************************************************************************
    *  onScrollChange
    **********************************************************************************************/
    override fun onScrollChange(v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
        if (scrollY == v!!.getChildAt(0).measuredHeight - v.measuredHeight){
            PAGE++
            animeViewModel!!.searchAnime(  page = PAGE)

        }

    }
}