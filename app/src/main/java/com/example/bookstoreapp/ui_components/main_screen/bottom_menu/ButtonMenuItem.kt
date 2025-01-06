package com.example.bookstoreapp.ui_components.main_screen.bottom_menu

import com.example.bookstoreapp.R

sealed class BottomMenuItem(
    val route: String,
    val title: String,
    val iconId: Int
) {
    object Home : BottomMenuItem(
        route = "home",
        title = "Home",
        iconId = R.drawable.ic_home
    )

    object Profile : BottomMenuItem(
        route = "profile",
        title = "Profile",
        iconId = R.drawable.ic_profil
    )

    object Settings : BottomMenuItem(
        route = "settings",
        title = "Settings",
        iconId = R.drawable.ic_settings
    )
}




