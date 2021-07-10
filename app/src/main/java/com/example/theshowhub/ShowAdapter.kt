package com.example.theshowhub

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.theshowhub.databinding.ItemShowBinding

class ShowAdapter: RecyclerView.Adapter<ShowAdapter.ViewHolder>() {

    private var shows = listOf<Show>()

    fun setShows(shows: List<Show>) {
        this.shows = shows
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
            ItemShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val show = shows[position]
        viewHolder.bind(show)
    }

    override fun getItemCount(): Int = shows.size

    override fun getItemId(position: Int): Long = shows[position].id.toLong()

    inner class ViewHolder(
            private val itemShowBinding: ItemShowBinding
    ): RecyclerView.ViewHolder(itemShowBinding.root) {

        fun bind(show: Show) {
            itemShowBinding.titleTextView.text = show.name

            Glide.with(itemShowBinding.root.context)
                .load(show.posterPath)
                .into(itemShowBinding.posterImageView)

            itemShowBinding.airDateTextView.text = show.firstAirDate
            itemShowBinding.voteTextView.text = show.voteAverage.toString()
        }

    }

}