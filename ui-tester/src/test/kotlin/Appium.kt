import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.AutomationName
import io.appium.java_client.remote.MobileCapabilityType
import io.appium.java_client.service.local.AppiumDriverLocalService
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException
import org.openqa.selenium.Platform
import org.openqa.selenium.remote.DesiredCapabilities

class Appium {
    private lateinit var service: AppiumDriverLocalService
    lateinit var androidDriver: AndroidDriver<MobileElement>

    fun startService() {
        service = AppiumDriverLocalService.buildDefaultService()
        service.start()
    }

    fun stopService() {
        service.stop()
    }

    fun launchAndroidApp(appFileName: String) {
        checkServiceIsRunning()
        val capabilities = DesiredCapabilities().apply {
            setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID)
            setCapability(MobileCapabilityType.PLATFORM_VERSION, "9")
            setCapability(MobileCapabilityType.DEVICE_NAME, "9887bc433753424b4b")
            setCapability(
                MobileCapabilityType.APP,
                "/Users/admin/Desktop/cross-platform-benchmark/bin/$appFileName"
            )
            setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2)
        }
        androidDriver = AndroidDriver(service.url, capabilities)
    }

    fun quitAndroidApp() {
        androidDriver.quit()
    }

    private fun checkServiceIsRunning() {
        if (!service.isRunning) {
            throw AppiumServerHasNotBeenStartedLocallyException("An appium server node is not started")
        }
    }
}