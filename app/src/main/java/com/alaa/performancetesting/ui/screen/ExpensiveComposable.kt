package com.alaa.performancetesting.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.tracing.trace
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.unit.dp

@Composable
fun ExpensiveListScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        LazyColumn {
            items(100) { index ->
                ExpensiveComposable(index)
            }
        }
    }
}

@Composable
fun ExpensiveComposable(index: Int, modifier: Modifier = Modifier) {
    // Wrap the expensive logic in a trace block
    val expensiveText = remember(index) {
        trace("ExpensiveCalculation for item $index") {
            simulateExpensiveWork(index)
        }
    }

    Text(
        text = expensiveText,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

fun simulateExpensiveWork(index: Int): String {
    // Simulate CPU delay (this is just dummy math work)
    var result = 0L
    for (i in 1..100_000) {
        result += (i * index) % 97
    }
    return "Result $index = $result"
}
