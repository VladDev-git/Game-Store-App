package com.example.bookstoreapp.ui_components.admin.admin_panel_screen.data

import com.example.bookstoreapp.R

sealed class MenuItem(val icon: Int, val title: String) {
    data object AddGame : MenuItem(icon = R.drawable.ic_home, title = "Add Game")
    data object EditGame : MenuItem(icon = R.drawable.ic_settings, title = "Edit Game")
    data object DeleteGame : MenuItem(icon = R.drawable.ic_profil, title = "Delete Game")
    data object AddCategory : MenuItem(icon = R.drawable.ic_home, title = "Add Category")
    data object EditCategory : MenuItem(icon = R.drawable.ic_settings, title = "Edit Category")
    data object DeleteCategory : MenuItem(icon = R.drawable.ic_profil, title = "Delete Category")
}