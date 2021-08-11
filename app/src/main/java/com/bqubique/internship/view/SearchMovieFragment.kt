package com.bqubique.internship.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.bqubique.internship.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SearchMovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val etSearchMovie: EditText = view.findViewById(R.id.etMovieSearch)
        val fab : FloatingActionButton = view.findViewById(R.id.fabSearch)
        fab.setOnClickListener {
            val action: NavDirections = SearchMovieFragmentDirections.actionSearchMovieFragmentToMovieFragment()

            Navigation.findNavController(it).navigate(action)
        }

    }

}