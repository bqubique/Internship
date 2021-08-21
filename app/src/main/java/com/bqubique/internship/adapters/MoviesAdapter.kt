package com.bqubique.internship.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bqubique.internship.R
import com.bqubique.internship.databinding.ItemMovieBinding
import com.bqubique.internship.model.Result
import com.bqubique.internship.view.SearchMovieFragmentDirections

class MoviesAdapter(var movies: ArrayList<Result>) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    inner class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.tvMovieName.text = movies[position].originalTitle
        holder.binding.itemLayout.setOnClickListener{
            val action = SearchMovieFragmentDirections.actionSearchMovieFragmentToMovieFragment(movies[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount() = movies.size


}