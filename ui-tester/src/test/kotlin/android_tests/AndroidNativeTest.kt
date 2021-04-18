package android_tests

import Appium
import android_framework.ViewingEventsUseCase
import org.junit.jupiter.api.*

class AndroidNativeTest {
    companion object {
        private val appium = Appium()

        private val useCase = ViewingEventsUseCase(
            targetName = "AndroidNative",
            packageName = "com.example.android_app"
        )
    }

    @BeforeEach
    fun beforeEach() {
        appium.launchAndroidApp("android-native-debug.apk", "MainActivity")
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