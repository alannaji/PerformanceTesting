package com.alaa.performancetesting.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.tracing.trace
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.tracing.Trace

@Composable
fun ExpensiveListScreen_Bg_Thread(
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
fun ExpensiveComposable(index: Int) {
    var result by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(index) {
        Trace.beginSection("AsyncExpensiveCalculation $index")
        try {
            result = withContext(Dispatchers.Default) {
                simulateExpensiveWork(index)
            }
        } finally {
            Trace.endSection()
        }
    }

    Text(
        text = result ?: "Loading...",
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}
