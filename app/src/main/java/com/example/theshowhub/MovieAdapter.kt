package com.example.theshowhub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movies = listOf<Movie>()

    fun setMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
    )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val movie = movies[position]

        viewHolder.bind(movie) {

        }
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie, onClick: () -> Unit) {
            itemView.findViewById<TextView>(R.id.titleTextView).text = movie.name
        }

    }

}