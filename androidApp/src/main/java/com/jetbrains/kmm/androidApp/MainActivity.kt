package com.jetbrains.kmm.androidApp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jetbrains.kmm.androidApp.theme.BookHavenTheme
import com.jetbrains.kmm.androidApp.ui.HomeScreen
import com.jetbrains.kmm.androidApp.ui.LoginScreen
import com.jetbrains.kmm.androidApp.ui.SplashScreen
import com.jetbrains.kmm.shared.Greeting

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookHavenTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "splash"
                ) {
                    composable("splash") {
                        SplashScreen(navController)
                    }
                    composable("login") {
                        HomeScreen()
                    }
                    composable("home") {
                        //HomeScreen()
                    }
                }
            }
        }
    }
}