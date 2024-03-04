package com.jetbrains.kmm.androidApp.presentations.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jetbrains.androidApp.R
import com.jetbrains.kmm.androidApp.router.Route
import com.jetbrains.kmm.androidApp.ui.LoginViewModel
import com.jetbrains.kmm.androidapp.theme.BookHavenTheme
import com.jetbrains.kmm.androidApp.theme.ColorLightGrey
import com.jetbrains.kmm.androidApp.theme.DarkGray
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = koinViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()
    LoginScreen(
        emailValue = uiState.value.email,
        passwordValue = uiState.value.password,
        onEmailChanged = { viewModel.setEmail(it) },
        onPasswordChanged = { viewModel.setPassword(it) },
        onButtonAccessClicked = { navController.navigate(Route.HOME.name) },
        onButtonRegisterClicked = { }
    )
}

@Composable
private fun LoginScreen(
    emailValue: String,
    passwordValue: String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onButtonAccessClicked: () -> Unit,
    onButtonRegisterClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Column {
            SectionLogo()
            SectionInputFields(
                emailValue = emailValue,
                passwordValue = passwordValue,
                onEmailChanged = onEmailChanged,
                onPasswordChanged = onPasswordChanged
            )
            AccessButton(onClick = onButtonAccessClicked)
            Spacer(modifier = Modifier.weight(1F))
            RegisterButton(onClick = onButtonRegisterClicked)
        }
    }
}

@Composable
private fun SectionLogo() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 36.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_books_logo),
            contentDescription = null,
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
private fun SectionInputFields(
    emailValue: String,
    passwordValue: String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))
        EmailTextField(
            email = emailValue,
            onValueChange = onEmailChanged,
        )
        PasswordTextField(
            password = passwordValue,
            onValueChange = onPasswordChanged,
        )
    }
}

@Composable
private fun EmailTextField(
    email: String,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        value = email,
        onValueChange = onValueChange,
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
}

@Composable
private fun PasswordTextField(
    password: String,
    onValueChange: (String) -> Unit,
) {
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    OutlinedTextField(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 18.dp, vertical = 14.dp),
        value = password,
        onValueChange = onValueChange,
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
                    if (passwordHidden) painterResource(id = R.drawable.ic_eye_close) else painterResource(
                        id = R.drawable.ic_eye_open
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

@Composable
private fun AccessButton(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
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
                text = stringResource(id = R.string.login_access),
                color = Color.White,
                style = MaterialTheme.typography.h2,
            )
        }
    }
}

@Composable
private fun RegisterButton(onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Divider(color = Color.LightGray, thickness = 1.dp)
    }
    TextButton(
        modifier = Modifier.padding(16.dp),
        onClick = { onClick() }
    ) {
        Text(
            text = "Não tenho uma conta. Toque para criar uma agora.",
            color = Color.Gray,
            style = MaterialTheme.typography.h3,
        )
    }
}

@Preview
@Composable
private fun LoginPreview() {
    BookHavenTheme {
        LoginScreen(
            emailValue = "larissa@email.com",
            passwordValue = "",
            onEmailChanged = {},
            onPasswordChanged = {},
            onButtonAccessClicked = {},
            onButtonRegisterClicked = {},
        )
    }
}
