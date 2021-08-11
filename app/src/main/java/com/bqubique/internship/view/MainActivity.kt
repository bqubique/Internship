package com.bqubique.internship.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.bqubique.internship.R
import com.bqubique.internship.service.MovieService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

//        val button: Button = findViewById(R.id.button)
//        val etMovieName: EditText = findViewById(R.id.etMovieName)
//
//        etMovieName.addTextChangedListener(
//            object : TextWatcher {
//                override fun beforeTextChanged(
//                    s: CharSequence?,
//                    start: Int,
//                    count: Int,
//                    after: Int
//                ) {
//
//                }
//
//                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                    if (count % 3 == 0) {
//                        Log.d("MAINACT", s.toString());
//                    }
//                }
//
//                override fun afterTextChanged(s: Editable?) {
////                    Log.d("MAINACT",s.toString());
//                }
//
//            }
//        )
//        button.setOnClickListener {
//            GlobalScope.launch {
//                val response = MovieService().api.getMovies(query = etMovieName.text.toString())
//                Log.d("MAINACT", response.toString())
//            }
//        }
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}