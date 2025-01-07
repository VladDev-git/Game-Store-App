package com.example.bookstoreapp.ui_components.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
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
        onSignUpSuccess: () -> Unit,
        onSignUpFailed: (String) -> Unit
    ) {
        if (email.isBlank() || password.isBlank()) {
            onSignUpFailed("Email or password is empty")
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onSignUpSuccess()
                } else {
                    onSignUpFailed(it.exception?.message ?: "Sign Up error")
                }
            }
    }

    fun signIn(
        auth: FirebaseAuth,
        email: String,
        password: String,
        onSignInSuccess: () -> Unit,
        onSignInFailed: (String) -> Unit
    ) {
        if (email.isBlank() || password.isBlank()) {
            onSignInFailed("Email or password is empty")
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onSignInSuccess()
                } else {
                    onSignInFailed(it.exception?.message ?: "Sign In error")
                }
            }
    }
}