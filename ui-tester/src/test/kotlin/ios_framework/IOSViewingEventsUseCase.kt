package ios_framework

import io.appium.java_client.MobileElement
import io.appium.java_client.PerformsTouchActions
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.ios.IOSTouchAction
import io.appium.java_client.touch.WaitOptions
import io.appium.java_client.touch.offset.PointOption
import org.openqa.selenium.Dimension
import java.time.Duration

class IOSViewingEventsUseCase {
    companion object {
        const val OPENED_EVENTS = 100
        const val SCROLLS_BETWEEN_OPENED_EVENTS = 3

        const val INITIAL_TIME = 5_000L
        const val READING_TIME = 2_000L
        const val SCROLL_INTERVAL = 1_000L
        const val SCROLL_DURATION = 500L
    }

    fun execute(driver: IOSDriver<MobileElement>) {
        Thread.sleep(INITIAL_TIME)
        repeat(OPENED_EVENTS) {
            repeat(SCROLLS_BETWEEN_OPENED_EVENTS) {
                driver.scrollDown()
                Thread.sleep(SCROLL_INTERVAL)
            }
            Thread.sleep(READING_TIME)
            driver.openItem()
            Thread.sleep(READING_TIME)
        }
    }

    private fun IOSDriver<MobileElement>.scrollDown() {
        val dimension: Dimension = manage().window().size
        val scrollStart = dimension.getHeight() * 0.7
        val scrollEnd = dimension.getHeight() * 0.2
        scrollVertically(yStart = scrollStart.toInt(), yEnd = scrollEnd.toInt())
    }

    private fun IOSDriver<MobileElement>.scrollVertically(yStart: Int, yEnd: Int) {
        IOSTouchAction(this as PerformsTouchActions)
            .press(PointOption.point(100, yStart))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(SCROLL_DURATION)))
            .moveTo(PointOption.point(100, yEnd))
            .release()
            .perform()
    }

    private fun IOSDriver<MobileElement>.openItem() {
        val dimension: Dimension = manage().window().size
        val xCenter = dimension.getWidth() * 0.5
        val yFirst = dimension.getHeight() * 0.3
        clickAtPoint(x = xCenter.toInt(), y = yFirst.toInt())
    }

    private fun IOSDriver<MobileElement>.clickAtPoint(x: Int, y: Int) {
        IOSTouchAction(this)
            .press(PointOption.point(x, y))
            .release()
            .perform()
    }
}