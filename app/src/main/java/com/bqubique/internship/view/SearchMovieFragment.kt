package com.bqubique.internship.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.bqubique.internship.R
import com.bqubique.internship.service.MovieService
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchMovieFragment : Fragment() {
    private lateinit var textField: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val fab: FloatingActionButton = view.findViewById(R.id.fabSearch)
        textField = view.findViewById(R.id.tietMovieSearch)

        textField.addTextChangedListener(object : TextWatcher {
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
                    Log.d("MAINACT", s.toString());
                    GlobalScope.launch {
                        searchMovie(s.toString())
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
//                    Log.d("MAINACT",s.toString());
            }

        })



        fab.setOnClickListener {
            val action: NavDirections =
                SearchMovieFragmentDirections.actionSearchMovieFragmentToMovieFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }

    fun searchMovie(movieName: String?) {
        GlobalScope.launch {
            val response = MovieService().api.getMovies(query = textField.text.toString())
            Log.d("MAINACT", response.toString())
        }
    }

}