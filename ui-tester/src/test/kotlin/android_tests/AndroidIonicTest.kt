package android_tests

import android_framework.AppiumAndroid
import android_framework.ViewingEventsUseCase
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test

class AndroidIonicTest {
    companion object {
        private val appium = AppiumAndroid()

        @BeforeClass
        @JvmStatic
        fun beforeClass() {
            appium.setup("android-ionic.apk")
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
            targetName = "AndroidIonic",
            packageName = "io.ionic.starter"
        ).execute()
    }
}