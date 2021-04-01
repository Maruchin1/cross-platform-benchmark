import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.ios.IOSElement
import io.appium.java_client.remote.AutomationName
import io.appium.java_client.remote.MobileCapabilityType
import io.appium.java_client.service.local.AppiumDriverLocalService
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException
import org.openqa.selenium.Platform
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities

class Appium {
    private lateinit var service: AppiumDriverLocalService
    lateinit var androidDriver: AndroidDriver<MobileElement>
    lateinit var iosDriver: IOSDriver<MobileElement>

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

    fun launchIosApp(bundleId: String) {
        checkServiceIsRunning()
        val capabilities = DesiredCapabilities().apply {
            setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.IOS)
            setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.4")
            setCapability(MobileCapabilityType.UDID, "00008030-001D583E2168802E")
            setCapability(MobileCapabilityType.DEVICE_NAME, "Wojtek's iPhone")
            setCapability(MobileCapabilityType.NO_RESET, true)
            setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST)
            setCapability("bundleId", bundleId)
            setCapability("xcodeOrgId", "G4T3QSBX3F")
            setCapability("xcodeSigningId", "iPhone Developer")
//            setCapability("useNewWDA", false)
            setCapability("updatedWDABundleId", "com.maruchin.WebDriverAgentRunner")
        }
        iosDriver = IOSDriver(service.url, capabilities)
    }

    fun quitAndroidApp() {
        androidDriver.quit()
    }

    fun quitIOSApp() {
        iosDriver.quit()
    }

    private fun checkServiceIsRunning() {
        if (!service.isRunning) {
            throw AppiumServerHasNotBeenStartedLocallyException("An appium server node is not started")
        }
    }
}