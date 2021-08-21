package com.bqubique.internship.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.navArgs
import coil.load
import coil.size.Scale
import com.bqubique.internship.api.MovieApi
import com.bqubique.internship.databinding.FragmentMovieBinding
import com.bqubique.internship.model.MovieDetails
import com.bqubique.internship.service.MovieService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding

    @Inject
    lateinit var movieApi: MovieApi

    private val args by navArgs<MovieFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivMovieBackgroundImage.load("https://image.tmdb.org/t/p/w500${args.selectedMovie.posterPath}")
        binding.ivMovieBackgroundImage.scaleType = ImageView.ScaleType.FIT_CENTER
        val movieDetails = retrieveMovieDetails(args.selectedMovie.id.toString())

        binding.tvMovieTitle.text = movieDetails.title
        binding.tvImdbScore.text = "IMDB ID: ${movieDetails.imdbId}"
    }

    private fun retrieveMovieDetails(movieId: String): MovieDetails {
        lateinit var retrievedDetails: MovieDetails
        val wait = CoroutineScope(Dispatchers.IO).launch {
            retrievedDetails =
                movieApi.getMovieDetails(movieId = movieId)
        }
        runBlocking {
            wait.join()
        }
        return retrievedDetails
    }
}