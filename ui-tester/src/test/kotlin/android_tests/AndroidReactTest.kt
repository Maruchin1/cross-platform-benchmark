package android_tests

import android_framework.AppiumAndroid
import android_framework.ViewingEventsUseCase
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test

class AndroidReactTest {
    companion object {
        private val appium = AppiumAndroid()

        @BeforeClass
        @JvmStatic
        fun beforeClass() {
            appium.setup("android-react.apk")
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
            targetName = "AndroidReact",
            packageName = "com.reactapp"
        ).execute()
    }
}