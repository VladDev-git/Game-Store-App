package com.example.bookstoreapp.ui_components.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookstoreapp.R
import com.example.bookstoreapp.ui.theme.DarkBlue

@Composable
fun DrawerHeader(
    email: String = "",
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
            .background(DarkBlue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(90.dp),
            painter = painterResource(id = R.drawable.logo_game_store_app),
            contentDescription = "logo",
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Game Store App",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = email,
            color = Color.Gray,
            fontSize = 12.sp,
        )
    }
}