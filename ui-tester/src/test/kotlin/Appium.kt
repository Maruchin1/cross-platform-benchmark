import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.ios.IOSElement
import io.appium.java_client.remote.AndroidMobileCapabilityType
import io.appium.java_client.remote.AutomationName
import io.appium.java_client.remote.MobileCapabilityType
import io.appium.java_client.service.local.AppiumDriverLocalService
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException
import org.openqa.selenium.Platform
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL

class Appium {
    lateinit var androidDriver: AndroidDriver<MobileElement>
    lateinit var iosDriver: IOSDriver<MobileElement>

    fun launchAndroidApp(packageName: String, activityName: String) {
        val capabilities = DesiredCapabilities().apply {
            setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID)
            setCapability(MobileCapabilityType.PLATFORM_VERSION, "10")
            setCapability(MobileCapabilityType.DEVICE_NAME, "WCR7N18525020561")
//            setCapability(AndroidMobileCapabilityType.APP_PACKAGE, packageName)
//            setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, activityName)
            setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2)
        }
        androidDriver = AndroidDriver(capabilities)
    }

    fun launchIosApp(bundleId: String) {
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
        iosDriver = IOSDriver(capabilities)
    }

    fun quitAndroidApp() {
        androidDriver.quit()
    }

    fun quitIOSApp() {
        iosDriver.quit()
    }
}