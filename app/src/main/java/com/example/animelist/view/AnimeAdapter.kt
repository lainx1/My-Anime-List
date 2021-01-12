package com.example.animelist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animelist.R
import com.example.animelist.model.Anime
import com.example.animelist.view.interfaces.AnimeInterface

class AnimeAdapter(private val animes: MutableList<Anime>, private val animeInterface: AnimeInterface):RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    /// ViewHolder
    class AnimeViewHolder(view:View):RecyclerView.ViewHolder(view)

    //Adapter metods

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_anime,parent,false)
        return AnimeViewHolder(view = view)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {

        val animeCard = holder.itemView.findViewById<CardView>(R.id.animeCard)

        val coverIv = holder.itemView.findViewById<ImageView>(R.id.coverIv)
        val titleTv = holder.itemView.findViewById<TextView>(R.id.TitleTV)
        val episodesTv = holder.itemView.findViewById<TextView>(R.id.episodesTV)
        val scoreTv = holder.itemView.findViewById<TextView>(R.id.scoreTV)

        val anime = animes[position]

        animeCard.setOnClickListener { animeInterface.onClickAnime(anime = anime) }

        titleTv.text = anime.title
        episodesTv.text = "Capitulos: ${anime.title}"
        scoreTv.text = "Calification: ${anime.score}"
        Glide.with(holder.itemView.context)
                .load(anime.image_url)
                .centerCrop()
                .into(coverIv)
    }

    override fun getItemCount(): Int = animes.size

    /*==============================================================================================
    PUBLIC METHODS
    ==============================================================================================*/
    fun addAnimes(animes: List<Anime>) {
        this.animes.addAll(animes)
        notifyDataSetChanged()
    }

    fun clearAnimes(){
        this.animes.clear()
        notifyDataSetChanged()
    }
}