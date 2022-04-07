package com.ndroid.moviesapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ndroid.moviesapp.data.entities.Movie
import com.ndroid.moviesapp.databinding.ItemMovieBinding
import javax.inject.Inject

class MoviesAdapter @Inject constructor() :
    ListAdapter<Movie, MoviesAdapter.ViewHolder>(MoviesDiffCallback()) {

    private var onItemClickListener : ((Movie) -> Unit)? = null

    fun setOnItemClickListener( onItemClickListener: ((Movie) -> Unit)) {
        this.onItemClickListener = onItemClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemMovieBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.apply {
            bind(currentItem)
            itemMovieBinding.root.setOnClickListener {
                onItemClickListener?.let { onItemClick -> onItemClick(currentItem) }
            }
        }
    }

    inner class ViewHolder(
        var itemMovieBinding: ItemMovieBinding,
    ) :
        RecyclerView.ViewHolder(itemMovieBinding.root) {

        fun bind(movie: Movie) {
            itemMovieBinding.movie = movie
            /*
            This call is an optimization that asks data binding to execute any pending bindings right away.
            It's always a good idea to call executePendingBindings() when you use binding adapters in a RecyclerView,
            because it can slightly speed up sizing the views.
             */
            itemMovieBinding.executePendingBindings()
        }

    }

    class MoviesDiffCallback : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }
}
