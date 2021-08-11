package com.bqubique.internship.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bqubique.internship.R
import com.bqubique.internship.model.Result

class MoviesAdapter(var movies: ArrayList<Result>) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val movieName: TextView = view.findViewById(R.id.tvMovieName)
        private val movieImage: ImageView = view.findViewById(R.id.ivMovieImage)
        fun bind(Result: Result) {
            movieName.text = Result.originalTitle

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= MovieViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}