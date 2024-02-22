package com.jetbrains.kmm.androidApp.ui

import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jetbrains.androidApp.R
import com.jetbrains.kmm.androidApp.theme.BookHavenTheme
import com.jetbrains.kmm.androidApp.theme.ColorBlue
import com.jetbrains.kmm.androidApp.theme.ColorLightGrey
import com.jetbrains.kmm.androidApp.theme.DarkGray

@Composable
fun Login(
    navController: NavController = rememberNavController()
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        SessionLogo()
        AddData()
        AccessButton(onClick = { Toast.makeText(context, "Acessar", Toast.LENGTH_LONG).show() })
        SessionRegister()
    }
}

@Composable
private fun SessionLogo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 36.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.book_logo),
            contentDescription = "icon inicial",
            modifier = Modifier.size(130.dp),
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "BookHaven",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground,
        )
    }
}

@Composable
private fun AddData() {
    var email by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "E-mail", style = MaterialTheme.typography.h1) },
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = ColorLightGrey,
                disabledBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = DarkGray,
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 16.dp),
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
            label = {
                Text(text = "Password", style = MaterialTheme.typography.h1)
            },
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = ColorLightGrey,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = DarkGray,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { passwordHidden = !passwordHidden }) {
                    val visibilityIcon =
                        if (passwordHidden) painterResource(id = R.drawable.eye_close) else painterResource(
                            id = R.drawable.eye_open
                        )
                    // Please provide localized description for accessibility services
                    val description = if (passwordHidden) "Show password" else "Hide password"
                    Image(
                        painter = visibilityIcon,
                        contentDescription = description,
                        modifier = Modifier.size(24.dp)
                    )
                }
            })
    }

}

@Composable
fun AccessButton(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(bottom = 90.dp)
            .fillMaxSize()
            .padding(horizontal = 18.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .size(48.dp)
                .clip(RoundedCornerShape(16.dp)),
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
            )
        ) {
            Text(
                text = "Acessar", color = Color.White, style = MaterialTheme.typography.h2
            )
        }
    }
}

@Composable
fun SessionRegister() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Divider(
            color = Color.LightGray, thickness = 1.dp
        )
    }
    Spacer(modifier = Modifier.height(4.dp))

    RegisterButton(onClick = {
        Toast.makeText(context, "Registrar novo usuario!", Toast.LENGTH_LONG).show()
    })
}

@Composable
fun RegisterButton(onClick: () -> Unit) {
    val activeTextColor = ColorBlue
    val inactiveTextColor = Color.Gray
    var click by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() },
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(onClick = { onClick() }) {
            Text(
                text = "Não tenho uma conta. Toque para criar uma agora.",
                color = inactiveTextColor,
                style = MaterialTheme.typography.h3,
            )
        }
    }
}

@Preview
@Composable
private fun LoginPreview() {
    BookHavenTheme {
        Login()
    }
}
