package com.alaa.performancetesting.ui.screen

import NumberRow
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.trace
import kotlinx.coroutines.delay

@Composable
fun LazyColumnScreen(
    modifier: Modifier = Modifier
) {
    val ticker = remember { mutableIntStateOf(0) }

    // ⚠️ Artificially triggers recomposition every 10ms
    LaunchedEffect(Unit) {
        while (true) {
            ticker.intValue++
            delay(10)
        }
    }
    trace("Lazy Column Screen") {
        Box(modifier = modifier) {
            trace("MainScreen LazyColumn") {
                LazyColumn(modifier = modifier) {
                    items(200) { index ->
                        trace("NumberText") {
                            NumberRow(
                                index = index,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                ticker = ticker.intValue
                            )
                        }
                    }
                }
            }

        }
    }
}