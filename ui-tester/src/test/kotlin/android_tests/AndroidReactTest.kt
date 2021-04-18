package android_tests

import Appium
import android_framework.ViewingEventsUseCase
import org.junit.jupiter.api.*

class AndroidReactTest {
    companion object {
        private val appium = Appium()

        private val useCase = ViewingEventsUseCase(
            targetName = "AndroidReact",
            packageName = "com.reactapp"
        )
    }

    @BeforeEach
    fun beforeEach() {
        appium.launchAndroidApp(
            packageName = "com.reactapp",
            activityName = "MainActivity"
        )
    }

    @AfterEach
    fun afterEach() {
        appium.quitAndroidApp()
    }

    @Test
    fun test() {
        useCase.execute(appium.androidDriver)
    }
}