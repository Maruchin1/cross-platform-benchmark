import java.io.File

class ResourcesMonitor {

    private val outputFile: File by lazy {
        File("resources_stats.txt").apply { createNewFile() }
    }

    private lateinit var process: Process

    fun startMonitoring(monitoringTimeSeconds: Long) {
        process = startMonitoringProcess(monitoringTimeSeconds)
    }

    fun waitForFinish() {
        val exitCode = process.waitFor()
        assert(exitCode == 0)
    }

    fun stopMonitoring() {
        process.destroy()
    }

    private fun startMonitoringProcess(monitoringTimeSeconds: Long): Process {
        val iterations = monitoringTimeSeconds + 30
        return ProcessBuilder().run {
            redirectOutput(outputFile)
            command("adb", "shell", "vmstat", "1", iterations.toString())
            start()
        }
    }
}