package com.bqubique.internship.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bqubique.internship.R
import com.bqubique.internship.databinding.ItemMovieBinding
import com.bqubique.internship.model.Result
import com.bqubique.internship.view.ItemOnClickListener
import com.bqubique.internship.view.SearchMovieFragmentDirections
import javax.inject.Inject
import javax.inject.Named

class MoviesAdapter(var movies: ArrayList<Result>) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>(), ItemOnClickListener {

    inner class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.tvMovieName.text = movies[position].originalTitle
        holder.binding.listener = this
        holder.binding.movieDetails = movies[position]
        holder.binding.ivMovieBackgroundImage.load("https://image.tmdb.org/t/p/w500${movies[position].posterPath}")
    }

    override fun getItemCount() = movies.size

    override fun onItemClick(view: View) {
        for(movie in movies){
            if(movie.id == view.tag){
                val action =
                    SearchMovieFragmentDirections.actionSearchMovieFragmentToMovieFragment(movie)
                Navigation.findNavController(view).navigate(action)
            }
        }
    }
}