package com.example.bookstoreapp.ui_components.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bookstoreapp.ui_components.login.data.MainScreenDataObject
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

): ViewModel() {
    val auth = mutableStateOf(Firebase.auth)

    fun signUp(
        auth: FirebaseAuth,
        email: String,
        password: String,
        onSignUpSuccess: (MainScreenDataObject) -> Unit,
        onSignUpFailed: (String) -> Unit
    ) {
        if (email.isBlank() || password.isBlank()) {
            onSignUpFailed("Email or password is empty")
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSignUpSuccess(
                        MainScreenDataObject(
                            uid = task.result.user?.uid!!,
                            email = task.result.user?.email!!
                        )
                    )
                } else {
                    onSignUpFailed(task.exception?.message ?: "Sign Up error")
                }
            }
    }

    fun signIn(
        auth: FirebaseAuth,
        email: String,
        password: String,
        onSignInSuccess: (MainScreenDataObject) -> Unit,
        onSignInFailed: (String) -> Unit
    ) {
        if (email.isBlank() || password.isBlank()) {
            onSignInFailed("Email or password is empty")
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSignInSuccess(
                        MainScreenDataObject(
                            uid = task.result.user?.uid!!,
                            email = task.result.user?.email!!
                        )
                    )
                } else {
                    onSignInFailed(task.exception?.message ?: "Sign In error")
                }
            }
    }

    fun isAdmin(
        onAdmin: (Boolean) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val uid = Firebase.auth.currentUser?.uid
        if (uid == null) {
            onFailure("User is not authenticated")
            return
        }

        Firebase.firestore.collection("admin")
            .document(uid)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val isAdmin = document.getBoolean("isAdmin") ?: false
                    onAdmin(isAdmin)
                } else {
                    onAdmin(false)
                }
            }
            .addOnFailureListener { e ->
                onFailure(e.message ?: "Failed to fetch admin data")
            }
    }
}