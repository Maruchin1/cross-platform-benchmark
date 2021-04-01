package ios_tests

import Appium
import ios_framework.IOSViewingEventsUseCase
import org.junit.jupiter.api.*

class IOSFlutterTest {
    companion object {
        const val BUNDLE_ID = "com.maruchin.benchmark-flutter-app"

        private val appium = Appium()
        private val useCase = IOSViewingEventsUseCase()

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
        appium.launchIosApp(BUNDLE_ID)
    }

    @AfterEach
    fun afterEach() {
        appium.quitIOSApp()
    }

    @Test
    fun test() {
        useCase.execute(appium.iosDriver)
    }
}