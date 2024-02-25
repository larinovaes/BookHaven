package com.jetbrains.kmm.androidApp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetbrains.kmm.androidApp.theme.BookHavenTheme

@Composable
fun DetailsScreen() {
    Box(
        modifier = Modifier
            .background(Color.White)
    ) {
        Toolbar()
    }
}
@Composable
private fun Toolbar() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {

                    Text(
                        textAlign = TextAlign.Center,
                        text = "Details",
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.onBackground,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {  }) {
                        Icon(painter = rememberVectorPainter(image = Icons.Default.ArrowBack ), contentDescription = null )
                    }
                },
            backgroundColor = Color.White
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
        ) {
            Divider(color = Color.LightGray, thickness = 1.dp)
            Text(
                modifier = Modifier
                    .padding(top = 24.dp, start = 18.dp, end = 18.dp),
                maxLines = 2,
                text = "A cinco passos de voce",
                style = MaterialTheme.typography.h1
            )

            Text(
                modifier = Modifier
                    .padding(top = 24.dp, start = 18.dp, end = 18.dp),
                text = "Stella Grant gosta de estar no controle. Ela parece ser uma adolescente típica, mas em sua rotina há listas de tarefas e inúmeros remédios que ela deve tomar para controlar a fibrose cística, uma doença crônica que impede que seus pulmões funcionem como deveriam.",
                style = MaterialTheme.typography.body2,
            )
        }
    }

}
@Preview
@Composable
private fun DetailsPreview() {
    BookHavenTheme {
        DetailsScreen()
    }
}