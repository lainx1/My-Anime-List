package com.example.animelist.view.activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.animelist.R
import com.example.animelist.viemodel.AnimeViewModel
import com.example.animelist.view.AnimeAdapter
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.scrollView

class SearchActivity : AppCompatActivity(),SearchView.OnQueryTextListener, NestedScrollView.OnScrollChangeListener {

    private var animeViewModel: AnimeViewModel? = null
    private var PAGE = 1
    private var query = ""
    private val animeAdapter = AnimeAdapter(animes = mutableListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        animeViewModel = AnimeViewModel()

        with(animeResultListRV) {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@SearchActivity, 2, GridLayoutManager.VERTICAL, false)
            adapter = animeAdapter
        }

        animeViewModel!!.search.observe(this, {
            animeAdapter.addAnimes(it.results)
            ///
        })

        handleIntent(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu,menu)

        val searchView =  (menu!!.findItem(R.id.search).actionView as SearchView)

        //Asocia al menu de busqueda
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

       searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
           isIconifiedByDefault = false
           requestFocus()
           setOnQueryTextListener(this@SearchActivity)
        }

        return true
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleIntent(intent!!)
    }
    private fun handleIntent(intent: Intent) {

        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            //use the query to search your data somehow
            Toast.makeText(this, query, Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleSearch(query: String?, clearSearch: Boolean = false){
        if (clearSearch){
            PAGE = 1
            animeAdapter.clearAnimes()

        }

        if (query != null && query.length > 2){
            animeViewModel!!.searchAnime(q = query, page = PAGE)



        }
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        this.query = query!!

        handleSearch(query = query,  clearSearch = true)
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        this.query = query!!

        handleSearch(query = query,  clearSearch = true)
        return true
    }

    override fun onScrollChange(
        v: NestedScrollView?,
        scrollX: Int,
        scrollY: Int,
        oldScrollX: Int,
        oldScrollY: Int
    ) {
        if (scrollY == v!!.getChildAt(0).measuredHeight - v.measuredHeight){
            PAGE++
           handleSearch(query)

        }
    }

    override fun onStart() {
        super.onStart()
        scrollView.setOnScrollChangeListener(this)

    }

}
