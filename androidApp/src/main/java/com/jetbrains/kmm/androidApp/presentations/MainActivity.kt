package com.jetbrains.kmm.androidApp.presentations

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jetbrains.kmm.androidApp.presentations.screens.BookDetailsScreen
import com.jetbrains.kmm.androidApp.presentations.screens.DownloadScreen
import com.jetbrains.kmm.androidApp.presentations.screens.HomeScreen
import com.jetbrains.kmm.androidApp.router.Route
import com.jetbrains.kmm.androidApp.presentations.screens.LoginScreen
import com.jetbrains.kmm.androidApp.presentations.screens.MyBooksScreen
import com.jetbrains.kmm.androidApp.presentations.screens.SplashScreen
import com.jetbrains.kmm.androidapp.theme.BookHavenTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookHavenTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Route.SPLASH.name
                ) {
                    composable(Route.SPLASH.name) {
                        SplashScreen(navController)
                    }
                    composable(Route.LOGIN.name) {
                        LoginScreen(navController)
                    }
                    composable(Route.HOME.name) {
                        HomeScreen(navController)
                    }
                    composable(Route.SIDE_MENU.name) {
                        Text(text = "Side Menu")
                    }
                    composable(Route.PROFILE.name) {
                        Text(text = "Profile")
                    }
                    composable(Route.DOWNLOAD.name) {
                        DownloadScreen()
                    }
                    composable(Route.MY_BOOKS.name) {
                        MyBooksScreen()
                    }
                    composable(Route.BOOK_DETAILS.name + "/{bookId}") {
                        BookDetailsScreen(navController)
                    }
                }
            }
        }
    }
}