package com.jetbrains.kmm.androidApp.ui

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetbrains.androidApp.R
import com.jetbrains.kmm.androidApp.enums.StateMenu
import com.jetbrains.kmm.androidApp.theme.BookHavenTheme
import com.jetbrains.kmm.androidApp.theme.ColorLightGrey
import com.jetbrains.kmm.androidApp.theme.DarkGray
import com.jetbrains.kmm.androidApp.theme.Gainsboro
import com.jetbrains.kmm.androidApp.theme.LightGrey
import com.jetbrains.kmm.androidApp.ui.model.Books

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .background(Color.White)
    ) {
        Toolbar()
        Column {
            SearchBar()
            Spacer(modifier = Modifier.height(16.dp))
            ChipSection(
                chips = listOf(
                    StateMenu.Download,
                    StateMenu.MY_BOOKS,
                    StateMenu.STORE,
                    StateMenu.PALL
                    ),
                onItemClick = {}
            )
            Spacer(modifier = Modifier.height(16.dp))
            AvailableBooksSession()
            Books(
                book = listOf(
                    Books(title = "A cinco passos de voce",
                        image = R.drawable.cinco_passos_de_voce_livro
                    ),
                    Books(title = "Ela fica com A Garota",
                        image = R.drawable.ela_fica_com_a_garota
                    ),
                    Books(title = "Princesa das cinzas",
                        image = R.drawable.princesa_das_cinzas
                    ),
                    Books(title = "Princesa das cinzas",
                        image = R.drawable.romeu_e_julieta
                    )
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar() {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "BookHaven",
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.onBackground,
                    )
                },
                navigationIcon = {
                    Menu(onClick = { Toast.makeText(context, "Acessar", Toast.LENGTH_LONG).show() })
                },
                actions = {
                    Profile(onClick = { Toast.makeText(context, "Acessar", Toast.LENGTH_LONG).show() })
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
            Text(
                modifier = Modifier
                    .padding(top = 24.dp, start = 18.dp, end = 18.dp),
                maxLines = 2,
                text = "Good morning, Larissa...",
                style = MaterialTheme.typography.h4
            )
        }
    }
}


@Composable
private fun SearchBar() {
    var book by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(top = 190.dp),
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
private fun Menu(onClick: () -> Unit) {
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
private fun Profile(onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .size(64.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.Transparent),
        elevation = 4.dp,
    ) {
        IconButton(onClick = { onClick }) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(LightGrey),
                alignment = Alignment.Center,
                painter = painterResource(id = R.drawable.heedo),
                contentDescription = "profile image",
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Composable
private fun ChipSection(
    chips: List<StateMenu>,
    onItemClick: () -> Unit
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
                        .clickable { onItemClick },
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
private fun AvailableBooksSession() {
    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {

        Divider(color = Color.LightGray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = "Available Books",
            Modifier.padding(start = 18.dp),
            style = MaterialTheme.typography.h4
        )
    }
}
@Composable
private fun Books(book: List<Books>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(book.size) {
                ListBook(books = book[it], onClick = {})
            }
        }
    }

}

@Composable
private fun ListBook(books: Books, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onClick }) {
        Surface(
            modifier = Modifier
            .height(184.dp)
            .width(184.dp)
            .padding(top = 16.dp, start = 8.dp)
        ) {
            Image(painter = painterResource(id = books.image), contentDescription = null,)

        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = books.title,
            Modifier.padding(start = 18.dp),
            style = MaterialTheme.typography.h1,
        )
    }
}


@Preview
@Composable
private fun HomePreview() {
    BookHavenTheme {
        HomeScreen()
    }
}