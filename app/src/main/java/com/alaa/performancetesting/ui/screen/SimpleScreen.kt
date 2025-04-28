package com.alaa.performancetesting.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun SimpleScreen(modifier: Modifier = Modifier) {
    val list = List(100) { "Item $it" }

    // 👇 BAD: Shared state that changes
    var randomState by remember { mutableIntStateOf(0) }

    // 👇 BAD: Triggering random state update when scrolling
    LaunchedEffect(Unit) {
        while (true) {
            kotlinx.coroutines.delay(500) // change every 500ms
            randomState++ // Triggers recomposition
        }
    }

    LazyColumn(modifier = modifier) {
        items(list) { item ->
            // 👇 BAD: Reading shared state inside each Text
            Text(text = "$item - $randomState")
        }
    }
}
