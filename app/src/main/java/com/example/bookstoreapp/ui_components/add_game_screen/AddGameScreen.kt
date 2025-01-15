package com.example.bookstoreapp.ui_components.add_game_screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.bookstoreapp.R
import com.example.bookstoreapp.data.Game
import com.example.bookstoreapp.ui.theme.DarkBlue
import com.example.bookstoreapp.ui_components.login.LoginButton
import com.example.bookstoreapp.ui_components.login.RoundedCornerTextField

@Composable
fun AddGameScreen(
    addGameScreenViewModel: AddGameScreenViewModel = hiltViewModel(),
    onSaved: () -> Unit,
) {

    val titleState = remember {
        mutableStateOf("")
    }
    val descriptionState = remember {
        mutableStateOf("")
    }
    val priceState = remember {
        mutableStateOf("")
    }
    val selectImageUri = remember {
        mutableStateOf<Uri?>(null)
    }

    var selectedCategory = "Simulators"

    val imageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        selectImageUri.value = uri
    }

    Image(
        painter = if (selectImageUri.value != null) {
            rememberAsyncImagePainter(model = selectImageUri.value)
        } else {
            painterResource(id = R.drawable.admin_panel_back)
        },
        contentDescription = "background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Box(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.6f))
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 40.dp, end = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
//        Image(
//            painter = painterResource(id = R.drawable.logo_game_store_app),
//            contentDescription = "logo",
//            modifier = Modifier.size(280.dp)
//        )
        Box(
            modifier = Modifier
                .wrapContentSize()
                .border(
                    width = 5.dp,
                    color = DarkBlue,
                    shape = RoundedCornerShape(20.dp)
                )
                .clip(shape = RoundedCornerShape(20.dp))
        ) {
            Image(
                painter = if (selectImageUri.value != null) {
                    rememberAsyncImagePainter(model = selectImageUri.value)
                } else {
                    painterResource(id = R.drawable.admin_panel_back)
                },
                contentDescription = "logo",
                modifier = Modifier
                    .size(240.dp)
                    .clip(shape = RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Add new game",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        RoundedCornerDropDownMenu { selectedItem ->
            selectedCategory = selectedItem
        }
        Spacer(modifier = Modifier.height(10.dp))
        RoundedCornerTextField(
            text = titleState.value,
            label = "Title",
        ) {
            titleState.value = it
        }
        Spacer(modifier = Modifier.height(10.dp))
        RoundedCornerTextField(
            maxLines = 5,
            singleLine = false,
            text = descriptionState.value,
            label = "Description",
        ) {
            descriptionState.value = it
        }
        Spacer(modifier = Modifier.height(10.dp))
        RoundedCornerTextField(
            text = priceState.value,
            label = "Price",
        ) {
            priceState.value = it
        }
        Spacer(modifier = Modifier.height(10.dp))
        LoginButton(
            text = "Select Image",
        ) {
            imageLauncher.launch("image/*")
        }
        LoginButton(
            text = "Save",
        ) {
            addGameScreenViewModel.saveGameImage(
                uri = selectImageUri.value!!,
                storage =  addGameScreenViewModel.storage.value,
                firestore = addGameScreenViewModel.firestore.value,
                game = Game(
                    title = titleState.value,
                    description = descriptionState.value,
                    price = priceState.value,
                    category = selectedCategory,
                ),
                onSaved = {
                    onSaved()
                },
                onError = {

                }
            )
        }
    }
}