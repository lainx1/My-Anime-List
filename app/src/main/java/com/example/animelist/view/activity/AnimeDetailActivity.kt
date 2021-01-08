package com.example.animelist.view.activity

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.animelist.R
import com.example.animelist.model.Anime
import com.example.animelist.viemodel.AnimeViewModel
import kotlinx.android.synthetic.main.activity_anime_detail.*
import kotlinx.android.synthetic.main.loader.*

class AnimeDetailActivity : BaseActivity() {

    val animeViewModel = AnimeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_detail)
        val anime =  intent.getSerializableExtra("anime") as Anime

        animeViewModel.anime.observe(this, {
            Glide.with(this@AnimeDetailActivity)
                .load(it.image_url)
                .centerCrop()
                .into(coverIv)
            toolbar.title = it.title

            animeViewModel.loading.observe(this,{
                showloader(
                    loader = loader,
                    loading = it
                )
            })

        })

        animeViewModel.findAnimeByid(anime.mal_id)

    }

    override fun onStart() {
        super.onStart()
        setSupportActionBar(materialToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(true)

        val arrow = ContextCompat.getDrawable(this,R.drawable.abc_ic_ab_back_material)
        arrow!!.setColorFilter(resources.getColor(R.color.white), PorterDuff.Mode.SRC_ATOP)
        supportActionBar!!.setHomeAsUpIndicator(arrow)
    }
}