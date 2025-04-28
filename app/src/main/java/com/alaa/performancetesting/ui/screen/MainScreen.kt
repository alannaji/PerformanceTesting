import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val ticker = remember { mutableIntStateOf(0) }

    // ⚠️ Artificially triggers recomposition every 50ms
    LaunchedEffect(Unit) {
        while (true) {
            ticker.value++
            delay(50)
        }
    }

    Box(modifier = modifier) {
        LazyColumn(modifier = modifier) {
            items(100) { index ->
                // 🚨 Every item depends on ticker.value = RECOMPOSITION BAIT
                NumberRow(
                    index = index,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    ticker = ticker.value
                )
            }
        }
    }
}

@Composable
fun NumberRow(index: Int, modifier: Modifier = Modifier, ticker: Int) {
    Text(
        text = "Item $index (Tick: $ticker)",
        modifier = modifier
    )
}
