package com.example.bookstoreapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.bookstoreapp.ui.theme.BookStoreAppTheme
import com.example.bookstoreapp.ui_components.add_game_screen.AddGameScreen
import com.example.bookstoreapp.ui_components.add_game_screen.data.AddScreenObject
import com.example.bookstoreapp.ui_components.login.LoginScreen
import com.example.bookstoreapp.ui_components.login.data.LoginScreenObject
import com.example.bookstoreapp.ui_components.login.data.MainScreenDataObject
import com.example.bookstoreapp.ui_components.main_screen.MainScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            BookStoreAppTheme {
                val systemUiController = rememberSystemUiController()
                val darkTheme = true

                if (darkTheme) {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = false
                    )
                } else {
                    systemUiController.setSystemBarsColor(
                        color = Color.White,
                        darkIcons = true
                    )
                }

                //LoginScreen()
                //MainScreen()

                NavHost(
                    navController = navController,
                    startDestination = LoginScreenObject
                ) {
                    composable<LoginScreenObject> {
                        LoginScreen { navData ->
                            navController.navigate(navData)
                        }
                    }
                    composable<MainScreenDataObject> { navEntry ->
                        val navData = navEntry.toRoute<MainScreenDataObject>()
                        MainScreen(navData) {
                            navController.navigate(AddScreenObject)
                        }
                    }
                    composable<AddScreenObject> {
                        AddGameScreen {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}




