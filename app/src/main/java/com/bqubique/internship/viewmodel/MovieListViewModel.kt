package com.bqubique.internship.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bqubique.internship.api.MovieApi
import com.bqubique.internship.model.Movie
import com.bqubique.internship.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    val movieApi: MovieApi
) : ViewModel()  {
    val movieList = MutableLiveData<List<Result>>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()

    fun refresh(movieName: String?){
        loading.value = true
        searchMovie(movieName)
    }

    fun searchMovie(movieName: String?){
        lateinit var response: Movie

        runBlocking {
            CoroutineScope(Dispatchers.IO).launch {
                response = movieApi.getMovies(query = movieName)

            }.join()
        }
        response.let {
            loading.value = false
            error.value = false
            movieList.value = response.results
        }
    }
}