package ios_tests

import Appium
import ios_framework.IOSViewingEventsUseCase
import org.junit.jupiter.api.*

class IOSNativeTest {
    companion object {
        const val BUNDLE_ID = "com.maruchin.ios-app"

        private val appium = Appium()
        private val useCase = IOSViewingEventsUseCase()
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