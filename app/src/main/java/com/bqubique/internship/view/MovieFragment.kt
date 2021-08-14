package com.bqubique.internship.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import coil.load
import coil.size.Scale
import com.bqubique.internship.R
import com.bqubique.internship.model.MovieDetails
import com.bqubique.internship.service.MovieService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MovieFragment : Fragment() {
    private val args by navArgs<MovieFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvMovieTitle = requireView().findViewById<TextView>(R.id.tvMovieTitle)
        val ivMovieImage = requireView().findViewById<ImageView>(R.id.ivMovieBackgroundImage)

        Log.d("MOVIE_FRAGMENT", args.selectedMovie.posterPath)
        ivMovieImage.load("https://image.tmdb.org/t/p/w500${args.selectedMovie.posterPath}"){
            scale(Scale.FIT)
        }
        ivMovieImage.scaleType = ImageView.ScaleType.FIT_CENTER
        val movieDetails = getMovieDetails(args.selectedMovie.id.toString())

        Log.d("MOVIE_FRAG", movieDetails.title.toString())
        tvMovieTitle.text = args.selectedMovie.id.toString()
    }

    fun getMovieDetails(movieId: String): MovieDetails{
        lateinit var retrievedDetails: MovieDetails
        val wait = GlobalScope.launch {
            retrievedDetails =  MovieService().api.getMovieDetails(movieId = args.selectedMovie.id.toString())

        }
        runBlocking {
            wait.join()
        }
        return retrievedDetails

    }
}