package com.jetbrains.kmm.androidApp.presentations.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jetbrains.androidApp.R
import com.jetbrains.kmm.androidApp.presentations.viewmodel.BookDetailsViewModel
import com.jetbrains.kmm.androidApp.theme.BookHavenTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookDetailsScreen(
    navController: NavController,
    viewModel: BookDetailsViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    BookDetailsScreen(
        bookImage = uiState.value.bookImage,
        title = uiState.value.title,
        description = uiState.value.description,
        authorName = uiState.value.authorName,
        onBackClick = { navController.popBackStack() }
    )
}

@Composable
private fun BookDetailsScreen(
    @DrawableRes bookImage: Int,
    title: String,
    description: String,
    authorName: String,
    onBackClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .background(Color.White)
    ) {
        Toolbar(onBackClick = onBackClick)
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            DetailsInfoBook(
                bookImage = bookImage,
                title = title,
                description = description,
                authorName = authorName,
            )
            DetailsSession()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar(
    onBackClick: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Details",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onBackground,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
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
private fun DetailsInfoBook(
    @DrawableRes bookImage: Int,
    title: String,
    description: String,
    authorName: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        Box(
            modifier = Modifier
                .height(226.dp)
        ) {
            Image(
                painter = painterResource(id = bookImage),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, start = 8.dp, end = 8.dp),
            maxLines = 2,
            text = title,
            style = MaterialTheme.typography.body1
        )
        Text(
            modifier = Modifier
                .padding(top = 16.dp, start = 8.dp, end = 8.dp),
            text = description,
            style = MaterialTheme.typography.h3,
        )

        Text(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 28.dp, start = 8.dp, end = 8.dp),
            maxLines = 2,
            text = "Autor: $authorName",
            style = MaterialTheme.typography.h3
        )
    }
}

@Composable
private fun DetailsSession() {
    var isLikeChecked by remember { mutableStateOf(false) }
    var isDownloadChecked by remember { mutableStateOf(false) }
    var isFavoriteChecked by rememberSaveable { mutableStateOf(false) }

    val likeResourceId =
        if(isLikeChecked) R.drawable.ic_heart_details_enable
        else R.drawable.ic_heart_details_disable

    val downloadResourceId =
        if (isDownloadChecked) R.drawable.ic_confirm
        else R.drawable.ic_download_details

    val favoriteResourceId =
        if (isDownloadChecked) R.drawable.ic_star_enable
        else R.drawable.ic_star_desanable

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 190.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .size(64.dp)
                .padding(vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.Right
        ) {
            IconButton(onClick = {
                isDownloadChecked = !isDownloadChecked
            }) {
                Image(
                    painter = painterResource(id = downloadResourceId),
                    contentDescription = "button download",
                    modifier = Modifier
                        .padding(start = 14.dp, end = 14.dp)
                )
            }

            IconButton(onClick = { isLikeChecked = !isLikeChecked }) {
                Image(
                    painter = painterResource(id = likeResourceId),
                    contentDescription = "button heart",
                    modifier = Modifier

                        .padding(start = 14.dp, end = 14.dp)
                )
            }

            IconButton(onClick = { isFavoriteChecked = !isFavoriteChecked }) {
                Image(
                    painter = painterResource(id = favoriteResourceId),
                    contentDescription = "button star",
                    modifier = Modifier
                        .padding(start = 14.dp, end = 14.dp)
                )
            }
        }
        Divider(color = Color.LightGray, thickness = 1.dp)
    }
}

@Preview
@Composable
private fun BookDetailsScreenPreview() {
    val title = "A cinco passos de voce"
    val description = "Stella Grant gosta de estar no controle. Ela parece ser uma adolescente típica..."
    val authorName = "Larissa"
    BookHavenTheme {
        BookDetailsScreen(
            bookImage = R.drawable.details,
            title = title,
            description = description,
            authorName = authorName,
        )
    }
}