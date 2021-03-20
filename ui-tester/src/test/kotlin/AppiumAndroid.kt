import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.AutomationName
import io.appium.java_client.remote.MobileCapabilityType
import io.appium.java_client.service.local.AppiumDriverLocalService
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException
import org.openqa.selenium.Platform
import org.openqa.selenium.remote.DesiredCapabilities

class AppiumAndroid {
    private lateinit var service: AppiumDriverLocalService
    lateinit var driver: AndroidDriver<MobileElement>

    fun setup(appFileName: String) {
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
                "/Users/admin/Desktop/cross-platform-benchmark/bin/$appFileName"
            )
            setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2)
        }
        driver = AndroidDriver(service.url, capabilities)
    }

    fun clear() {
        driver.quit()
        service.stop()
    }

}