package com.jetbrains.kmm.androidApp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jetbrains.kmm.androidApp.theme.BookHavenTheme
import com.jetbrains.kmm.androidApp.ui.DownloadScreen
import com.jetbrains.kmm.androidApp.ui.LoginScreen
import com.jetbrains.kmm.androidApp.ui.SplashScreen

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
                        //HomeScreen()
                    }
                }
            }
        }
    }
}