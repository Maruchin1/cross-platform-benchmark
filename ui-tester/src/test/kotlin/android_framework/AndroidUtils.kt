package android_framework


class AndroidUtils(
    private val dataExtractor: AndroidDataExtractor,
    private val packageName: String,
) {

    private lateinit var vmStat: Process

    fun startCpuStats(monitoringTimeSeconds: Long) {
        vmStat = ProcessBuilder()
            .command("adb", "shell", "vmstat", "1", monitoringTimeSeconds.toString())
            .redirectOutput(dataExtractor.cpuStatsFile)
            .start()
    }

    fun stopCpuStats() {
        vmStat.destroy()
    }

    fun saveFramesStats() {
        ProcessBuilder()
            .command("adb", "shell", "dumpsys", "gfxinfo", packageName)
            .redirectOutput(dataExtractor.frameStatsFile)
            .start()
            .waitFor()
    }

    fun resetRamStats() {
        ProcessBuilder()
            .command("adb", "shell", "dumpsys", "procstats", "--reset")
            .start()
            .waitFor()
    }

    fun saveRamStats() {
        ProcessBuilder()
            .command("adb", "shell", "dumpsys", "procstats", "--hours", "1", packageName)
            .redirectOutput(dataExtractor.ramStatsFile)
            .start()
            .waitFor()
    }

    fun resetBatteryStats() {
        ProcessBuilder()
            .command("adb", "shell", "dumpsys", "batterystats", "--reset")
            .start()
            .waitFor()
    }

    fun saveBatteryStats() {
        ProcessBuilder()
            .command("adb", "shell", "dumpsys", "batterystats", packageName)
            .redirectOutput(dataExtractor.batteryStatsFile)
            .start()
            .waitFor()
    }

    fun uninstallApp() {
        ProcessBuilder()
            .command("adb", "uninstall", packageName)
            .start()
            .waitFor()
    }
}