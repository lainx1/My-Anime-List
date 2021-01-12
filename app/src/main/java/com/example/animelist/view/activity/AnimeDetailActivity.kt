package com.example.animelist.view.activity

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.animelist.R
import com.example.animelist.model.Anime
import com.example.animelist.viemodel.AnimeViewModel
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_anime_detail.*
import kotlinx.android.synthetic.main.loader.*

class AnimeDetailActivity : BaseActivity() {

    val animeViewModel = AnimeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_detail)

        val anime = intent.getSerializableExtra("anime") as Anime

        animeViewModel.anime.observe(this, {

            //Load image
            Glide.with(this@AnimeDetailActivity)
                .load(it.image_url)
                .centerCrop()
                .into(coverIv)
            //Set toolbar title
            toolbar.title = it.title

            //Set details
            titleTV.text = it.title
            descriptionTV.text = it.synopsis
            statusTV.text = if(it.airing) resources.getStringArray(R.array.anime_status)[0] else resources.getStringArray(R.array.anime_status)[1]
            episodesTV.text = it.episodes.toString()
            scoreTV.text = if(it.score != null) it.score.toString() else "0.0"

            //Set genres
            it.genres?.forEach {

                with(Chip(this@AnimeDetailActivity)){
                    //this.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    //this.width = ViewGroup.LayoutParams.WRAP_CONTENT
                    this.text = it.name
                    this.setChipBackgroundColorResource(R.color.design_default_color_primary)
                    this.setTextColor(resources.getColor(R.color.white))
                    genresCG.addView(this)
                }
            }
        })

        animeViewModel.loading.observe(this, {
            showLoader(
                loader = loader,
                loading = it
            )
        })

        animeViewModel.findAnimeByid(id = anime.mal_id)
    }

    override fun onStart() {
        super.onStart()
        setSupportActionBar(materialToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(true)

        val arrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material)
        arrow!!.setColorFilter(resources.getColor(R.color.white), PorterDuff.Mode.SRC_ATOP)
        supportActionBar!!.setHomeAsUpIndicator(arrow)
    }

}