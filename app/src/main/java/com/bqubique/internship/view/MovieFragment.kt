package com.bqubique.internship.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import coil.load
import com.bqubique.internship.R
import com.bqubique.internship.api.MovieApi
import com.bqubique.internship.databinding.FragmentMovieBinding
import com.bqubique.internship.model.MovieDetails
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding

    @Inject
    lateinit var movieApi: MovieApi

    @Inject
    @Named("movieImageBaseUrl")
    lateinit var movieImageBaseUrl: String

    private val args by navArgs<MovieFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_movie, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivMovieBackgroundImage.load("$movieImageBaseUrl${args.selectedMovie.posterPath}")
        binding.ivMovieBackgroundImage.scaleType = ImageView.ScaleType.FIT_CENTER
        val movieDetails = retrieveMovieDetails(args.selectedMovie.id.toString())
        binding.movie = movieDetails
    }

    private fun retrieveMovieDetails(movieId: String): MovieDetails {
        lateinit var retrievedDetails: MovieDetails
        
        runBlocking {
            CoroutineScope(Dispatchers.IO).launch {
                retrievedDetails =
                    movieApi.getMovieDetails(movieId = movieId)
            }.join()
        }
        return retrievedDetails
    }
}