import io.appium.java_client.MobileElement
import io.appium.java_client.PerformsTouchActions
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidTouchAction
import io.appium.java_client.touch.WaitOptions
import io.appium.java_client.touch.offset.PointOption
import org.openqa.selenium.Dimension
import java.time.Duration

class ViewingEventsUseCase(
    private val driver: AndroidDriver<MobileElement>,
    targetName: String,
    packageName: String
) {
    companion object {
        const val OPENED_EVENTS = 2
        const val SCROLLS_BETWEEN_OPENED_EVENTS = 3

        const val INITIAL_TIME = 2_000L
        const val READING_TIME = 2_000L
        const val SCROLL_INTERVAL = 1_000L
        const val SCROLL_DURATION = 500L
    }

    private val androidUtils = AndroidUtils(targetName, packageName)

    fun execute() {
        androidUtils.run {
            dumpsysBatteryStatsReset()
            startVmStat(calculateExecutionTimeSeconds())
        }
        Thread.sleep(INITIAL_TIME)
        repeat(OPENED_EVENTS) {
            repeat(SCROLLS_BETWEEN_OPENED_EVENTS) {
                scrollDown()
                Thread.sleep(SCROLL_INTERVAL)
            }
            openItem()
            Thread.sleep(READING_TIME)
        }
        androidUtils.run {
            stopVmStat()
            dumpSysGfxInfo()
            dumpsysBatteryStats()
            uninstallApp()
        }
    }

    private fun scrollDown() {
        val dimension: Dimension = driver.manage().window().size
        val scrollStart = dimension.getHeight() * 0.9
        val scrollEnd = dimension.getHeight() * 0.4
        scrollVertically(
            yStart = scrollStart.toInt(),
            yEnd = scrollEnd.toInt()
        )
    }

    private fun scrollVertically(yStart: Int, yEnd: Int) {
        AndroidTouchAction(driver as PerformsTouchActions)
            .press(PointOption.point(0, yStart))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(SCROLL_DURATION)))
            .moveTo(PointOption.point(0, yEnd))
            .release()
            .perform()
    }

    private fun openItem() {
        val dimension: Dimension = driver.manage().window().size
        val xCenter = dimension.getWidth() * 0.5
        val yFirst = dimension.getHeight() * 0.3
        clickAtPoint(x = xCenter.toInt(), y = yFirst.toInt())
    }

    private fun clickAtPoint(x: Int, y: Int) {
        AndroidTouchAction(driver as PerformsTouchActions)
            .press(PointOption.point(x, y))
            .release()
            .perform()
    }

    private fun calculateExecutionTimeSeconds(): Long {
        val millis =
            INITIAL_TIME + OPENED_EVENTS * (SCROLLS_BETWEEN_OPENED_EVENTS * (SCROLL_DURATION + SCROLL_INTERVAL) + READING_TIME)
        return (millis / 1_000) + 30
    }
}