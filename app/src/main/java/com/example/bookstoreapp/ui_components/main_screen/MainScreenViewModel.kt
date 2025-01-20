package com.example.bookstoreapp.ui_components.main_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bookstoreapp.data.Game
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(

): ViewModel() {
    val firestore = mutableStateOf(Firebase.firestore)
    val gamesListState = mutableStateOf(emptyList<Game>())

    fun getAllGames(
        db: FirebaseFirestore,
        onGames: (List<Game>) -> Unit
    ) {
        db.collection("games")
            .get()
            .addOnSuccessListener { task ->
                onGames(task.toObjects(Game::class.java))
            }
            .addOnFailureListener {

            }
    }
}