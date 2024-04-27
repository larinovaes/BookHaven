package com.jetbrains.kmm.androidApp.presentations.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetbrains.androidApp.R
import com.jetbrains.kmm.androidapp.presentations.model.MyBooksModel
import com.jetbrains.kmm.androidApp.theme.BookHavenTheme

@Composable
fun MyBooksScreen() {
    Box(
        modifier = Modifier
            .background(Color.White)
    ) {
        Toolbar()
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp)) {
            ListBooks(
                book = listOf(
                    MyBooksModel(
                        title = "A cinco passos de voce",
                        image = R.drawable.cinco_passos_de_voce_livro,
                        subTitle = "por Rachael Lippincott (Autor)\n" +
                                "synopsis...",
                    ),
                    MyBooksModel(
                        title = "Ela fica com A Garota",
                        image = R.drawable.ela_fica_com_a_garota,
                        subTitle = "por Rachael Lippincott (Autor)\n" +
                                "synopsis...",
                    ),
                    MyBooksModel(
                        title = "Princesa das cinzas",
                        image = R.drawable.princesa_das_cinzas,
                        subTitle = "por Rachael Lippincott (Autor)\n" +
                                "synopsis...",
                    ),
                    MyBooksModel(
                        title = "Princesa das cinzas",
                        image = R.drawable.romeu_e_julieta,
                        subTitle = "por Rachael Lippincott (Autor)\n" +
                                "synopsis...",
                    ),
                    MyBooksModel(
                        title = "Princesa das cinzas",
                        image = R.drawable.romeu_e_julieta,
                        subTitle = "por Rachael Lippincott (Autor)\n" +
                                "synopsis...",
                    )
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "My Books",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onBackground,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = rememberVectorPainter(image = Icons.Default.ArrowBack),
                            contentDescription = null
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
        ) {
            Divider(color = Color.LightGray, thickness = 1.dp)
        }
    }
}

@Composable
private fun ListBooks(book: List<MyBooksModel>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(1)) {
            items(book.size) {
                InfoBook(books = book[it], onClick = {})
            }
        }
    }

}

@Composable
private fun InfoBook(books: MyBooksModel, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick },
    ) {
        Row(
            modifier = Modifier
                .height(150.dp)
                .width(390.dp)
                .padding(top = 16.dp, start = 8.dp)
        ) {
            Image(painter = painterResource(id = books.image), contentDescription = null)

            Spacer(modifier = Modifier.height(4.dp))

            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = books.title,
                    Modifier.padding(start = 18.dp),
                    style = MaterialTheme.typography.body1
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = books.subTitle,
                    Modifier.padding(start = 18.dp),
                    style = MaterialTheme.typography.h2,
                )
                Spacer(modifier = Modifier.height(8.dp))
                SessionLiked()
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color.LightGray, thickness = 1.dp)
    }
}

@Composable
private fun SessionLiked() {
    var isLikeChecked by remember { mutableStateOf(false) }
    var isFavoriteChecked by rememberSaveable { mutableStateOf(false) }

    val likeResourceId =
        if(isLikeChecked) R.drawable.ic_heart_details_enable
        else R.drawable.ic_heart_details_disable

    val favoriteResourceId =
        if (isFavoriteChecked) R.drawable.ic_star_enable
        else R.drawable.ic_star_desanable
    Row(
        Modifier
            .fillMaxWidth()
            .size(54.dp)
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.Right) {

        IconButton(
            modifier = Modifier
                .size(54.dp),
            onClick = { isLikeChecked = !isLikeChecked }
        ) {
            Image(
                painter = painterResource(id = likeResourceId),
                contentDescription = "button heart",
            )
        }

        IconButton(modifier = Modifier
            .size(54.dp),
            onClick = { isFavoriteChecked = !isFavoriteChecked }) {
            Image(
                painter = painterResource(id = favoriteResourceId),
                contentDescription = "button star",
            )
        }
    }
}

@Preview
@Composable
private fun MyBooksPreview() {
    BookHavenTheme {
        MyBooksScreen()
    }
}
