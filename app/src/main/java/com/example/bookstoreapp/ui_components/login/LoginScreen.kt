package com.example.bookstoreapp.ui_components.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bookstoreapp.R
import com.example.bookstoreapp.ui_components.login.data.MainScreenDataObject

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onNavigationToMainScreen: (MainScreenDataObject) -> Unit
) {

    val errorState = remember {
        mutableStateOf("")
    }
    val emailState = remember {
        mutableStateOf("")
    }
    val passwordState = remember {
        mutableStateOf("")
    }

    Image(painter = painterResource(
        id = R.drawable.log_screen_background),
        contentDescription = "background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Box(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.Blue.copy(alpha = 0.2f))
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 40.dp, end = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_game_store_app),
            contentDescription = "logo",
            modifier = Modifier.size(280.dp)
        )
        Text(
            text = "Game Store App",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        RoundedCornerTextField(
            text = emailState.value,
            label = "Email",
        ) {
            emailState.value = it
        }
        Spacer(modifier = Modifier.height(10.dp))
        RoundedCornerTextField(
            text = passwordState.value,
            label = "Password",
        ) {
            passwordState.value = it
        }
        Spacer(modifier = Modifier.height(10.dp))
        if (errorState.value.isNotEmpty()) {
            Text(
                text = errorState.value,
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }
        LoginButton(
            text = "Sign In",
        ) {
            loginViewModel.signIn(
                loginViewModel.auth.value,
                emailState.value,
                passwordState.value,
                onSignInSuccess = { navData ->
                    onNavigationToMainScreen(navData)
                },
                onSignInFailed = { error ->
                    errorState.value = error
                }
            )
        }
        LoginButton(
            text = "Sign Up",
        ) {
            loginViewModel.signUp(
                loginViewModel.auth.value,
                emailState.value,
                passwordState.value,
                onSignUpSuccess = { navData ->
                    onNavigationToMainScreen(navData)
                },
                onSignUpFailed = { error ->
                    errorState.value = error
                }
            )
        }
    }
}




