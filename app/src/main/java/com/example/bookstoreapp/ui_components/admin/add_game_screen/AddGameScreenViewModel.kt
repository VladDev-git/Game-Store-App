package com.example.bookstoreapp.ui_components.admin.add_game_screen

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bookstoreapp.data.Game
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddGameScreenViewModel @Inject constructor(

) : ViewModel() {
    val storage = mutableStateOf(Firebase.storage)
    val firestore = mutableStateOf(Firebase.firestore)

    fun saveGameImage(
        uri: Uri,
        storage: FirebaseStorage,
        firestore: FirebaseFirestore,
        game: Game,
        onSaved: () -> Unit,
        onError: () -> Unit
    ) {
        val timeStamp = System.currentTimeMillis()
        val storageRef = storage.reference
            .child("book_images")
            .child("image_$timeStamp.ipg")
        val uploadTask = storageRef.putFile(uri)
        uploadTask.addOnSuccessListener {
            storageRef.downloadUrl.addOnSuccessListener { url ->
                saveGameToFireStore(
                    firestore = firestore,
                    url = url.toString(),
                    game = game,
                    onSaved = {
                        onSaved()
                    },
                    onError = {
                        onError()
                    }
                )
            }
        }
    }

    fun saveGameToFireStore(
        firestore: FirebaseFirestore,
        url: String,
        game: Game,
        onSaved: () -> Unit,
        onError: () -> Unit
    ) {
        val db = firestore.collection("games")
        val key = db.document().id
        db.document(key)
            .set(
                game.copy(
                    imageUrl = url,
                    key = key
                )
            )
            .addOnSuccessListener {
                onSaved()
            }
            .addOnFailureListener {
                onError()
            }
    }
}


