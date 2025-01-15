package com.example.bookstoreapp.ui_components.add_game_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bookstoreapp.ui.theme.DarkBlue

@Composable
fun RoundedCornerDropDownMenu(
    onOptionSelected: (String) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val selectedOption = remember { mutableStateOf("Simulators") }
    val categoriesList = listOf(
        "Simulators", "Cooperative", "Fighters",
        "RPG", "Adventure", "Puzzle",
        "Horror", "Survival", "Shooter",
        "Platformer", "Strategy", "Racing"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = DarkBlue, shape = RoundedCornerShape(25.dp))
            .clip(shape = RoundedCornerShape(25.dp))
            .background(color = Color.White)
            .clickable {
                expanded.value = true
            }
            .padding(16.dp)
    ) {
        Text(
            text = selectedOption.value
        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier
                .heightIn(max = 230.dp),
            containerColor = Color.Gray
        ) {
            categoriesList.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        selectedOption.value = option
                        expanded.value = false
                        onOptionSelected(option)
                    }
                )
            }
        }
    }
}



