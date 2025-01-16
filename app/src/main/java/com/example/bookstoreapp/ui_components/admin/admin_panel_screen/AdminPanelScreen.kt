package com.example.bookstoreapp.ui_components.admin.admin_panel_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookstoreapp.R
import com.example.bookstoreapp.ui.theme.DarkBlue
import com.example.bookstoreapp.ui.theme.DarkGrey
import com.example.bookstoreapp.ui_components.main_screen.DrawerBody
import com.example.bookstoreapp.ui_components.main_screen.DrawerHeader
import com.example.bookstoreapp.ui_components.main_screen.bottom_menu.BottomMenu
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdminPanelScreen(
    onAddGameScreen: () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val menuItemsList = listOf(
        "Add game" to R.drawable.ic_settings.toString(),
        "Edit game" to R.drawable.ic_home.toString(),
        "Delete game" to R.drawable.ic_profil.toString(),
        "Add category" to R.drawable.ic_settings.toString(),
        "Edit category" to R.drawable.ic_home.toString(),
        "Delete category" to R.drawable.ic_profil.toString(),
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        modifier = Modifier.fillMaxWidth(),
        drawerContent = {
            Column(modifier = Modifier.fillMaxWidth(0.7f)) {
                DrawerHeader()
                DrawerBody {
                    coroutineScope.launch {
                        drawerState.close()
                    }
                    onAddGameScreen()
                }
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomMenu()
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkBlue)
                    .padding(paddingValues)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DarkGrey)
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(menuItemsList) { item ->
                            AdminPanelMenuItem(item) {

                            }
                        }
                        item {
                            Text(
                                text = "Log out",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {

                                    }
                                    .padding(20.dp)
                            )
                        }
                        item {
                            Text(
                                text = "Проблема у вирівнюванні тексту всередині Row. Ви використовуєте align(Alignment.CenterVertically)," +
                                        " але це працює лише на рівні дочірнього елемента. Щоб текст вирівнювався по вертикалі по центру в " +
                                        "межах контейнера Row, потрібно правильно налаштувати висоту Row і переконатися, що текст займає потрібне місце." +
                                        " Ось оновлений код:",
                                color = Color.White,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}