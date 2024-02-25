package com.jetbrains.kmm.androidApp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jetbrains.androidApp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController = rememberNavController()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_books_logo),
            contentDescription = "icon inicial",
            modifier = Modifier.size(150.dp),
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "BookHaven",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onBackground,
        )
    }

    LaunchedEffect(key1 = Unit) {
        delay(2000)
        navController.navigate("LOGIN")
    }
}


@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreen()
}