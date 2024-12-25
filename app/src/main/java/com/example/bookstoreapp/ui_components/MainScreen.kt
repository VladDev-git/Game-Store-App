package com.example.bookstoreapp.ui_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.bookstoreapp.data.Book
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun MainScreen(
    fs: FirebaseFirestore,
    onClick: () -> Unit,
               ) {
    val list = remember {
        mutableStateOf(emptyList<Book>())
    }

    val listener = fs.collection("books").addSnapshotListener { snapShot, exeption ->
        list.value = snapShot?.toObjects(Book::class.java) ?: emptyList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
        ) {
            items(list.value) { book ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        AsyncImage(
                            model = book.imageUrl, contentDescription = null,
                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp)
                        )
                        Text(
                            text = book.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth()
                                .padding(15.dp)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                onClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp, start = 10.dp, end = 10.dp, top = 10.dp)
        ) {
            Text(
                text = "Add Book"
            )
        }
    }
}





