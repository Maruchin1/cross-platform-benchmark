import java.io.File

class Terminal {

    fun startUiTest(target: Target): Process {
        return ProcessBuilder().run {
            command("./gradlew", "clean", ":test", "--tests", target.testName)
            start()
        }
    }

    fun startVmStat(outputFile: File, monitoringTimeSeconds: Long): Process {
        return ProcessBuilder().run {
            redirectOutput(outputFile)
            command("adb", "shell", "vmstat", "1", monitoringTimeSeconds.toString())
            start()
        }
    }

    fun uninstallApp(target: Target): Process {
        return ProcessBuilder().run {
            command("adb", "uninstall", target.packageName)
            start()
        }
    }
}