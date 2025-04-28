package com.alaa.performancetesting.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.tracing.trace

@Composable
fun BadPerformanceScreen_NoEffect(
    modifier: Modifier = Modifier
) {
    var ticker by remember { mutableIntStateOf(0) }

    Column {
        Button(
            onClick = { ticker++ },
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text("Recompose All ($ticker)")
        }

        // This will rebuild 1000 Text composables every time the button is clicked
        Column {
            repeat(100) { rowIndex ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    repeat(10) { colIndex ->
                        RecomposingText(row = rowIndex, col = colIndex, ticker = ticker)
                    }
                }
            }
        }
    }
}

@Composable
fun RecomposingText(row: Int, col: Int, ticker: Int) {
    Text(
        text = "[$row,$col] Tick: $ticker",
        modifier = Modifier
            .padding(4.dp)
    )
}
