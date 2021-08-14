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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

        Log.d("MOVIE_FRAGMENT", "${args.selectedMovie.posterPath}")
        ivMovieImage.load("https://image.tmdb.org/t/p/w500${args.selectedMovie.posterPath}"){
            scale(Scale.FIT)
        }
        ivMovieImage.scaleType = ImageView.ScaleType.FIT_CENTER


        tvMovieTitle.text = args.selectedMovie.id.toString()
    }

    fun getMovieDetails(){
        GlobalScope.launch {

        }
    }
}