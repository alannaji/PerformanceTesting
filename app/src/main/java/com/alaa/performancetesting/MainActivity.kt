package com.alaa.performancetesting

import MainScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alaa.performancetesting.ui.screen.BadPerformanceScreen_NoEffect
import com.alaa.performancetesting.ui.screen.ExpensiveListScreen
import com.alaa.performancetesting.ui.screen.ExpensiveListScreen_Bg_Thread
import com.alaa.performancetesting.ui.screen.SimpleScreen
import com.alaa.performancetesting.ui.theme.PerformanceTestingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PerformanceTestingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SimpleScreen(modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize())
                }
            }
        }
    }
}
