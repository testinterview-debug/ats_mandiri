package com.example.test_moviedb.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.test_moviedb.presentation.detail.MovieDetailScreen
import com.example.test_moviedb.presentation.genre.GenreScreen
import com.example.test_moviedb.presentation.movie.MovieListScreen
import com.example.test_moviedb.presentation.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(NavRoute.GenreList) {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        composable<NavRoute.GenreList> {
            GenreScreen(
                onNavigateToMovies = { id, name ->
                    navController.navigate(NavRoute.MovieList(id, name))
                }
            )
        }

        composable<NavRoute.MovieList> { backStackEntry ->
            val route: NavRoute.MovieList = backStackEntry.toRoute()
            MovieListScreen(
                genreId = route.genreId,
                genreName = route.genreName,
                onNavigateToDetail = { movieId ->
                    navController.navigate(NavRoute.MovieDetail(movieId))
                },
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }

        composable<NavRoute.MovieDetail> { backStackEntry ->
            val route: NavRoute.MovieDetail = backStackEntry.toRoute()
            MovieDetailScreen(
                movieId = route.movieId,
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}
