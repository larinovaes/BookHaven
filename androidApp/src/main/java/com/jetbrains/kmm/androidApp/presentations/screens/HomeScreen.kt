package com.jetbrains.kmm.androidApp.presentations.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jetbrains.androidApp.R
import com.jetbrains.kmm.androidApp.domain.model.BookModel
import com.jetbrains.kmm.androidApp.enums.OptionMenu
import com.jetbrains.kmm.androidApp.presentations.viewmodel.HomeViewModel
import com.jetbrains.kmm.androidApp.router.Route
import com.jetbrains.kmm.androidApp.theme.ColorLightGrey
import com.jetbrains.kmm.androidApp.theme.DarkGray
import com.jetbrains.kmm.androidApp.theme.Gainsboro
import com.jetbrains.kmm.androidApp.theme.LightGrey
import com.jetbrains.kmm.androidApp.theme.BookHavenTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    HomeScreen(
        profileImage = uiState.value.profileImage,
        isAvailableBooksLoading = uiState.value.isAvailableBooksLoading,
        welcomeText = uiState.value.welcomeText,
        bookModelList = uiState.value.bookModelList,
        onSideMenuClick = { navController.navigate(Route.SIDE_MENU.name) },
        onProfileMenuClick = { navController.navigate(Route.PROFILE.name) },
        onOptionMenuClick = {
            when (it) {
                OptionMenu.DOWNLOAD -> navController.navigate(Route.DOWNLOAD.name)
                OptionMenu.MY_BOOKS -> navController.navigate(Route.MY_BOOKS.name)
                OptionMenu.STORE -> Unit
                OptionMenu.BOOK_SHARE -> Unit
            }
        },
        onBookClick = { navController.navigate(Route.BOOK_DETAILS.name + "/${it.id}") },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    @DrawableRes profileImage: Int,
    welcomeText: String,
    isAvailableBooksLoading: Boolean,
    bookModelList: List<BookModel>,
    onSideMenuClick: () -> Unit,
    onProfileMenuClick: () -> Unit,
    onOptionMenuClick: (OptionMenu) -> Unit,
    onBookClick: (BookModel) -> Unit,
) {
    val gridColumn = 2
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Toolbar(
                profileImage = profileImage,
                onSideMenuClick = onSideMenuClick,
                onProfileMenuClick = onProfileMenuClick,
            )
        },
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(gridColumn),
            modifier = Modifier.padding(innerPadding)
        ) {
            item(span = { GridItemSpan(gridColumn) }) {
                Column {
                    WelcomeSection(welcomeText = welcomeText)
                    SearchBar()
                    Spacer(modifier = Modifier.height(16.dp))
                    OptionsSection(
                        chips = listOf(
                            OptionMenu.DOWNLOAD,
                            OptionMenu.MY_BOOKS,
                            OptionMenu.STORE,
                            OptionMenu.BOOK_SHARE
                        ),
                        onItemClick = onOptionMenuClick,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(color = Color.LightGray, thickness = 1.dp)
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "Available Books",
                        Modifier.padding(start = 18.dp),
                        style = MaterialTheme.typography.h4
                    )
                    if (isAvailableBooksLoading) AvailableBooksLoading()
                }
            }
            if (!isAvailableBooksLoading)
                items(bookModelList) { BookItem(book = it, onClick = onBookClick) }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar(
    @DrawableRes profileImage: Int,
    onSideMenuClick: () -> Unit,
    onProfileMenuClick: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "BookHaven",
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onBackground,
            )
        },
        navigationIcon = {
            SideMenu(onClick = onSideMenuClick)
        },
        actions = {
            ProfileMenu(
                profileImage = profileImage,
                onClick = onProfileMenuClick
            )
        },
        scrollBehavior = scrollBehavior,
    )
}

@Composable
private fun WelcomeSection(welcomeText: String) {
    Column(verticalArrangement = Arrangement.Top) {
        Divider(color = Color.LightGray, thickness = 1.dp)
        Text(
            modifier = Modifier
                .padding(top = 24.dp, start = 18.dp, end = 18.dp),
            maxLines = 2,
            text = welcomeText,
            style = MaterialTheme.typography.h4
        )
    }
}

@Composable
private fun SearchBar() {
    var book by remember { mutableStateOf("") }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 14.dp),
            value = book,
            shape = RoundedCornerShape(24.dp),
            onValueChange = { book = it },
            placeholder = {
                Text(text = "Search Book", style = MaterialTheme.typography.h1)
            },
            leadingIcon = {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = ColorLightGrey,
                disabledBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = DarkGray,
            ),
        )
    }
}

@Composable
private fun SideMenu(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(34.dp)
            .height(34.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        IconButton(onClick = { onClick() }) {
            Icon(
                modifier = Modifier
                    .width(34.dp)
                    .height(34.dp),
                contentDescription = "menu",
                painter = painterResource(id = R.drawable.ic_menu),
                tint = Color.Black
            )
        }
    }
}

@Composable
private fun ProfileMenu(
    @DrawableRes profileImage: Int,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .size(64.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.Transparent),
        elevation = 4.dp,
    ) {
        IconButton(onClick = onClick) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(LightGrey),
                alignment = Alignment.Center,
                painter = painterResource(id = profileImage),
                contentDescription = "profile image",
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Composable
private fun OptionsSection(
    chips: List<OptionMenu>,
    onItemClick: (OptionMenu) -> Unit
) {
    LazyRow {
        items(chips) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp, end = 14.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier
                        .padding(4.dp)
                        .clickable { onItemClick(it) },
                    shape = CircleShape,
                    border = BorderStroke(0.5.dp, Color.Transparent),
                    elevation = 2.dp,
                    color = Gainsboro
                ) {
                    Image(
                        painter = painterResource(id = it.icon),
                        contentDescription = null,
                        Modifier.padding(8.dp)
                    )
                }
                Text(
                    textAlign = TextAlign.Center,
                    text = it.title,
                    style = MaterialTheme.typography.h2
                )
            }
        }
    }

}

@Composable
private fun AvailableBooksLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun BookItem(book: BookModel, onClick: (BookModel) -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onClick(book) }) {
        Surface(
            modifier = Modifier
                .height(184.dp)
                .width(184.dp)
                .padding(top = 16.dp, start = 8.dp)
        ) {
            Image(painter = painterResource(id = book.image), contentDescription = null)

        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = book.title,
            Modifier.padding(start = 18.dp),
            style = MaterialTheme.typography.h1,
        )
    }
}

@Preview
@Composable
private fun HomePreview() {
    val bookModelList = listOf(
        BookModel(
            id = "1",
            title = "A cinco passos de voce",
            image = R.drawable.cinco_passos_de_voce_livro,
            description = "",
            authorName = "",
        ),
        BookModel(
            id = "1",
            title = "Ela fica com A Garota",
            image = R.drawable.ela_fica_com_a_garota,
            description = "",
            authorName = "",
        ),
        BookModel(
            id = "1",
            title = "Princesa das cinzas",
            image = R.drawable.princesa_das_cinzas,
            description = "",
            authorName = "",
        ),
        BookModel(
            id = "1",
            title = "Princesa das cinzas",
            image = R.drawable.romeu_e_julieta,
            description = "",
            authorName = "",
        )
    )
    BookHavenTheme {
        HomeScreen(
            profileImage = R.drawable.heedo,
            welcomeText = "Good morning, Larissa...",
            isAvailableBooksLoading = false,
            bookModelList = bookModelList,
            onSideMenuClick = {},
            onProfileMenuClick = {},
            onOptionMenuClick = {},
            onBookClick = {},
        )
    }
}
