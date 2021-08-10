package com.bqubique.internship.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.bqubique.internship.R
import com.bqubique.internship.service.MovieService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        val etMovieName: EditText = findViewById(R.id.etMovieName)

        etMovieName.addTextChangedListener(
            object: TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if(count %  3 ==0){
                        Log.d("MAINACT",s.toString());
                    }
                }

                override fun afterTextChanged(s: Editable?) {
//                    Log.d("MAINACT",s.toString());
                }

            }
        )
        button.setOnClickListener {
            GlobalScope.launch {
                val response = MovieService().api.getMovies()
                Log.d("MAINACT", response.title)
            }
        }
    }
}