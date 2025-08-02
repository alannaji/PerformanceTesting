import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.trace
import kotlinx.coroutines.delay

@Composable
fun ColumnBasedScreen(
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

    Box(modifier = modifier) {
        trace("Column Screen") {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                repeat(200) {
                    trace("NumberRow") {
                        NumberRow(
                            index = it,
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

@Composable
fun NumberRow(index: Int, modifier: Modifier = Modifier, ticker: Int) {
    Text(
        text = "Item $index (Tick: $ticker)",
        modifier = modifier
    )
}
