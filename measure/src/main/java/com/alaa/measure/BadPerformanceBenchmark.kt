package com.alaa.measure
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.UiSelector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BadPerformanceBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun testHeavyRecompositionOnButtonClick() = benchmarkRule.measureRepeated(
        packageName = "com.alaa.performancetesting", // Replace with your real package
        metrics = listOf(
            FrameTimingMetric(), // Tracks jank, frame overrun, P99
            StartupTimingMetric()
        ),
        iterations = 2,
        startupMode = StartupMode.COLD,
        setupBlock = {
            pressHome() // Ensure clean cold start
        }
    ) {
        startActivityAndWait()

        // Give the UI a moment to render
        device.waitForIdle()

        // Click the button multiple times to simulate recomposition pressure
        repeat(5) {
            device.findObject(
                UiSelector().textContains("Recompose All")
            ).click()

            device.waitForIdle()
        }
    }
}