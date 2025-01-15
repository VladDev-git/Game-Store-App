package com.example.bookstoreapp.ui_components.main_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bookstoreapp.R
import com.example.bookstoreapp.ui.theme.DarkTransparentBlue
import com.example.bookstoreapp.ui.theme.LightGrey
import com.example.bookstoreapp.ui_components.login.LoginViewModel

@Composable
fun DrawerBody(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onAdminPanelClick: () -> Unit
) {
    val categoriesList = listOf(
        "Store",
        "My library",
        "Favorites",
        "Categories",
        "Discounts",
        "Settings"
    )

    val isAdminState = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        loginViewModel.isAdmin(
            onAdmin = { isAdmin ->
                if (isAdmin) {
                    isAdminState.value = true
                } else {
                    isAdminState.value = false
                }
            },
            onFailure = { error ->
                Log.e("AdminCheck", "Error checking admin status: $error")
            }
        )
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Gray)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.log_screen_background),
            contentDescription = "background",
            alpha = 0.2f,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Categories",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(LightGrey)
            )
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(categoriesList) { item ->
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                        }
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = item,
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth()
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(LightGrey)
                        )
                    }
                }
            }
            if(isAdminState.value) Button(
                onClick = {
                    onAdminPanelClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkTransparentBlue
                )
            ) {
                Text(
                    text = "Admin panel",
                    color = Color.White
                )
            }
        }
    }
}