package com.bqubique.internship.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.bqubique.internship.R
import com.bqubique.internship.service.MovieService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            Log.d("MAINACT", "HEMLOOOOOO")
            GlobalScope.launch {
                val response = MovieService().api.getMovies()
                Log.d("MAINACT", response.toString())
            }
        }
    }
}