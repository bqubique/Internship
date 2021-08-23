package com.bqubique.internship.view

import android.opengl.Visibility
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bqubique.internship.adapters.MoviesAdapter
import com.bqubique.internship.api.MovieApi
import com.bqubique.internship.databinding.FragmentSearchMovieBinding
import com.bqubique.internship.model.Movie
import com.bqubique.internship.viewmodel.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class SearchMovieFragment : Fragment() {
    private lateinit var binding: FragmentSearchMovieBinding
    private lateinit var movieListViewModel: MovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieListViewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)

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
                    movieListViewModel.refresh(s.toString())
                    movieListViewModel.loading.observe(
                        viewLifecycleOwner,
                        Observer { loading ->
                            loading?.let {
                                if (it)
                                    binding.progressBar.visibility = View.VISIBLE
                                else
                                    binding.progressBar.visibility = View.GONE

                            }
                        }
                    )
                    movieListViewModel.movieList.observe(viewLifecycleOwner,
                        Observer { list ->
                            list?.let {
                                binding.rvResults.adapter = MoviesAdapter(ArrayList(it))
                            }
                        })
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}