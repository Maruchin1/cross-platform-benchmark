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

        @BeforeAll
        @JvmStatic
        fun beforeAll() {
            appium.startService()
        }

        @AfterAll
        @JvmStatic
        fun afterAll() {
            appium.stopService()
        }
    }

    @BeforeEach
    fun beforeEach() {
        appium.launchAndroidApp("android-native-debug.apk")
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