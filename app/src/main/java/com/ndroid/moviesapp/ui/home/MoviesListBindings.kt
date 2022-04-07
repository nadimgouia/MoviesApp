package com.ndroid.moviesapp.ui.home

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ndroid.moviesapp.data.entities.Movie

object MoviesListBindings {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun ImageView.setImageUrl(imageUrl: String?) {
        Glide.with(this)
            .load(imageUrl)
            .into(this)
    }

    @BindingAdapter("app:items")
    @JvmStatic
    fun RecyclerView.set(items: List<Movie>?) {
        (this.adapter as MoviesAdapter).submitList(items)
    }

}