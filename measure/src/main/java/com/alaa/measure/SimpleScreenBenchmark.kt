package com.alaa.measure

import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class SimpleScreenBenchmark {

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

        device.wait(Until.hasObject(By.text("Item 0")), 5_000)

        val displayWidth = device.displayWidth
        val displayHeight = device.displayHeight

        // Now perform two swipes
        repeat(2) {
            device.swipe(
                /* startX = */ displayWidth / 2,
                /* startY = */ (displayHeight * 0.6).toInt(),
                /* endX = */ displayWidth / 2,
                /* endY = */ (displayHeight * 0.3).toInt(),
                /* steps = */ 50 // 50 steps = slower swipe, smooth
            )
            device.waitForIdle()
        }
    }
}