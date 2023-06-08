package com.aliozdemir.cleanmovieapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aliozdemir.cleanmovieapp.presentation.movie_detail.views.MovieDetailScreen
import com.aliozdemir.cleanmovieapp.presentation.movies.views.MovieScreen
import com.aliozdemir.cleanmovieapp.presentation.ui.theme.CleanMovieAppTheme
import com.aliozdemir.cleanmovieapp.util.Constants.IMDB_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanMovieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface( modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = Screen.MovieScreen.route) {

                        composable(route = Screen.MovieScreen.route) {
                            MovieScreen(navController = navController)
                        }

                        composable(route = Screen.MovieDetailScreen.route+"/{${IMDB_ID}}") {
                            MovieDetailScreen()
                        }

                    }

                }
            }
        }
    }
}

