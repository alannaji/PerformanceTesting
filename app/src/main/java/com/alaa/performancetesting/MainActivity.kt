package com.alaa.performancetesting

import ColumnBasedScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.alaa.performancetesting.ui.screen.LazyColumnScreen
import com.alaa.performancetesting.ui.theme.PerformanceTestingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            PerformanceTestingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   // ColumnBasedScreen(modifier = Modifier.padding(innerPadding))
                    LazyColumnScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
