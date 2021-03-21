import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test

class AndroidIonicTest {
    companion object {
        private val appium = AppiumAndroid()

        @BeforeClass
        @JvmStatic
        fun beforeClass() {
            appium.setup("android-ionic-debug.apk")
        }

        @AfterClass
        @JvmStatic
        fun afterClass() {
            appium.clear()
        }
    }

    @Test
    fun test() {
        ViewingEventsUseCase(appium.driver).execute()
    }
}