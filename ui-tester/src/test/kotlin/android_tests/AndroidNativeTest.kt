package android_tests

import android_framework.AppiumAndroid
import android_framework.ViewingEventsUseCase
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test

class AndroidNativeTest {
    companion object {
        private val appium = AppiumAndroid()

        @BeforeClass
        @JvmStatic
        fun beforeClass() {
            appium.setup("android-native-debug.apk")
        }

        @AfterClass
        @JvmStatic
        fun afterClass() {
            appium.clear()
        }
    }

    @Test
    fun test() {
        ViewingEventsUseCase(
            driver = appium.driver,
            targetName = "AndroidNative",
            packageName = "com.example.android_app"
        ).execute()
    }
}