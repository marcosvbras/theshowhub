package com.example.theshowhub.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.theshowhub.R
import com.example.theshowhub.api.Show
import com.example.theshowhub.databinding.ItemShowBinding

class ShowAdapter: ListAdapter<Show, ShowAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
            ItemShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
        viewHolder.bind(getItem(position))

    override fun getItemCount(): Int = currentList.size

    override fun getItemId(position: Int): Long = getItem(position).id.toLong()

    private class DiffCallback : DiffUtil.ItemCallback<Show>() {

        override fun areItemsTheSame(oldItem: Show, newItem: Show) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Show, newItem: Show) =
            oldItem == newItem

    }

    inner class ViewHolder(
            private val itemShowBinding: ItemShowBinding
    ): RecyclerView.ViewHolder(itemShowBinding.root) {

        fun bind(show: Show) {
            itemShowBinding.titleTextView.text = show.name

            Glide.with(itemShowBinding.root.context)
                .load(show.posterPath)
                .placeholder(R.drawable.ic_poster_placeholder)
                .centerCrop()
                .into(itemShowBinding.posterImageView)

            itemShowBinding.airDateTextView.text = show.formattedAirDate
            itemShowBinding.voteTextView.text = show.voteAverage.toString()
        }

    }

}