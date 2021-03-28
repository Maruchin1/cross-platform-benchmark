package android_tests

import Appium
import android_framework.ViewingEventsUseCase
import org.junit.jupiter.api.*

class AndroidIonicTest {
    companion object {
        private val appium = Appium()

        private val useCase = ViewingEventsUseCase(
            targetName = "AndroidIonic",
            packageName = "io.ionic.starter"
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
        appium.launchAndroidApp("android-ionic.apk")
    }

    @AfterEach
    fun afterEach() {
        appium.quitAndroidApp()
    }

    @Test
    @RepeatedTest(3)
    fun test() {
        useCase.execute(appium.androidDriver)
    }
}