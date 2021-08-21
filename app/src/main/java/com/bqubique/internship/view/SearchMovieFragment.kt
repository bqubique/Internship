package com.bqubique.internship.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bqubique.internship.adapters.MoviesAdapter
import com.bqubique.internship.api.MovieApi
import com.bqubique.internship.databinding.FragmentSearchMovieBinding
import com.bqubique.internship.model.Movie
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class SearchMovieFragment : Fragment() {
    private lateinit var binding: FragmentSearchMovieBinding

    @Inject
    lateinit var t2: MovieApi

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchMovieBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)


        binding.rvResults.layoutManager =
            GridLayoutManager(view.context, 2, RecyclerView.VERTICAL, false)

        binding.tietMovieSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (count % 3 == 0) {
                    var s = searchMovie(s.toString())
                    binding.rvResults.adapter = MoviesAdapter(ArrayList(s.results))
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        binding.fabSearch.setOnClickListener {
        }

    }

    fun searchMovie(movieName: String?): Movie {
        lateinit var response: Movie
        val s = CoroutineScope(Dispatchers.IO).launch {
            response = t2.getMovies(query = movieName)
            Log.d("MAINACT", response.toString())

        }
        runBlocking {
            s.join()
        }
        return response
    }

}