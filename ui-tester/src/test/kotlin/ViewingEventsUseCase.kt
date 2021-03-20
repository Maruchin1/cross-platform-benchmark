import io.appium.java_client.MobileElement
import io.appium.java_client.PerformsTouchActions
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidTouchAction
import io.appium.java_client.touch.WaitOptions
import io.appium.java_client.touch.offset.PointOption
import org.openqa.selenium.Dimension
import java.time.Duration

class ViewingEventsUseCase(
    private val driver: AndroidDriver<MobileElement>
) {

    fun execute() {
        Thread.sleep(2_000)

        repeat(10) {
            repeat(5) {
                scrollDown()
                Thread.sleep(1_000)
            }
            openItem()
            Thread.sleep(2_000)
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
            .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
            .moveTo(PointOption.point(0, yEnd))
            .release()
            .perform()
    }

    private fun openItem() {
        val dimension: Dimension = driver.manage().window().size
        val xCenter = dimension.getWidth() * 0.5
        val yFirst = dimension.getHeight() * 0.3
        val ySecond = dimension.getHeight() * 0.6
        clickAtPoint(x = xCenter.toInt(), y = yFirst.toInt())
        clickAtPoint(x = xCenter.toInt(), y = ySecond.toInt())
    }

    private fun clickAtPoint(x: Int, y: Int) {
        AndroidTouchAction(driver as PerformsTouchActions)
            .press(PointOption.point(x, y))
            .release()
            .perform()
    }
}