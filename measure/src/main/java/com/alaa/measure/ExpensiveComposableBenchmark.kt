package com.alaa.measure

import androidx.benchmark.macro.*
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.uiautomator.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ExpensiveComposableBenchmark {

    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun scrollExpensiveListScreen() = benchmarkRule.measureRepeated(
        packageName = "com.alaa.performancetesting", // ✅ Replace with your actual package
        metrics = listOf(
            FrameTimingMetric(),
            StartupTimingMetric()
        ),
        iterations = 2,
        startupMode = StartupMode.COLD,
        setupBlock = {
            pressHome()
        }
    ) {
        startActivityAndWait()

        // Wait for content to render
        device.waitForIdle()

        // Scroll the list to trigger multiple recompositions
        val list = UiScrollable(UiSelector().scrollable(true))

        // Scroll forward 5 times
        repeat(5) {
            list.flingForward()
            device.waitForIdle()
        }

    }
}


