package android_tests

import Appium
import android_framework.ViewingEventsUseCase
import org.junit.jupiter.api.*

class AndroidFlutterTest {
    companion object {
        private val appium = Appium()

        private val useCase = ViewingEventsUseCase(
            targetName = "AndroidFlutter",
            packageName = "com.example.flutter_app"
        )
    }

    @BeforeEach
    fun beforeEach() {
        appium.launchAndroidApp("android-flutter.apk", "MainActivity")
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