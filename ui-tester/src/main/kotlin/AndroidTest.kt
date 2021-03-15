import io.appium.java_client.PerformsTouchActions
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.android.AndroidTouchAction
import io.appium.java_client.remote.AutomationName
import io.appium.java_client.remote.MobileCapabilityType
import io.appium.java_client.service.local.AppiumDriverLocalService
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException
import io.appium.java_client.touch.WaitOptions
import io.appium.java_client.touch.offset.PointOption
import org.openqa.selenium.Dimension
import org.openqa.selenium.Platform
import org.openqa.selenium.remote.DesiredCapabilities
import java.time.Duration

class AndroidTest : Test() {

    private lateinit var service: AppiumDriverLocalService
    private lateinit var driver: AndroidDriver<AndroidElement>

    override fun setup() {
        service = AppiumDriverLocalService.buildDefaultService()
        service.start()
        if (!service.isRunning) {
            throw AppiumServerHasNotBeenStartedLocallyException("An appium server node is not started")
        }
        val capabilities = DesiredCapabilities().apply {
            setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID)
            setCapability(MobileCapabilityType.PLATFORM_VERSION, "10")
            setCapability(MobileCapabilityType.DEVICE_NAME, "RF8MA0NM1CP")
            setCapability(
                MobileCapabilityType.APP,
                "/Users/admin/Desktop/cross-platform-benchmark/bin/app-debug.apk"
            )
            setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2)
        }
        driver = AndroidDriver(service.url, capabilities)
    }

    override fun test() {
        Thread.sleep(2_000)

        repeat(10) {
            repeat(5) {
                scrollDown()
                scrollDelay()
            }
            openItem()
            Thread.sleep(2_000)
        }
    }

    override fun clear() {
        driver.quit()
        service.stop()
    }

    private fun scrollDown() {
        val dimension: Dimension = driver.manage().window().size
        val scrollStart = (dimension.getHeight() * 0.9).toInt()
        val scrollEnd = (dimension.getHeight() * 0.4).toInt()
        AndroidTouchAction(driver as PerformsTouchActions)
            .press(PointOption.point(0, scrollStart))
            .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
            .moveTo(PointOption.point(0, scrollEnd))
            .release().perform()
    }

    private fun scrollDelay() {
        Thread.sleep(1_000)
    }

    private fun openItem() {
        val itemCard = driver.findElementsById("com.example.android_app:id/event_name")[0]
        itemCard.click()
    }
}