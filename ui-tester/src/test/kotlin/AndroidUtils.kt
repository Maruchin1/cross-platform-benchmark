import java.io.File

class AndroidUtils(
    private val target: String,
    private val packageName: String,
) {

    private val resultDir: File by lazy {
        File("results").apply { if (!exists()) mkdir() }
    }

    private lateinit var vmStat: Process

    fun startVmStat(monitoringTimeSeconds: Long) {
        vmStat = ProcessBuilder()
            .command("adb", "shell", "vmstat", "1", monitoringTimeSeconds.toString())
            .redirectOutput(newVmStatFile())
            .start()
    }

    fun stopVmStat() {
        vmStat.destroy()
    }

    fun dumpSysGfxInfo() {
        ProcessBuilder()
            .command("adb", "shell", "dumpsys", "gfxinfo", packageName)
            .redirectOutput(newDumpSysGfxInfoFile())
            .start()
            .waitFor()
    }

    fun dumpsysBatteryStatsReset() {
        ProcessBuilder()
            .command("adb", "shell", "dumpsys", "batterystats", "--reset")
            .start()
            .waitFor()
    }

    fun dumpsysBatteryStats() {
        ProcessBuilder()
            .command("adb", "shell", "dumpsys", "batterystats", packageName)
            .redirectOutput(newDumpSysBatteryStatsFile())
            .start()
            .waitFor()
    }

    fun uninstallApp() {
        ProcessBuilder()
            .command("adb", "uninstall", packageName)
            .start()
            .waitFor()
    }

    private fun newVmStatFile(): File {
        val timestamp = System.currentTimeMillis()
        val fileName = "vmstat_${target}_${timestamp}.txt"
        return File(resultDir, fileName).apply { createNewFile() }
    }

    private fun newDumpSysGfxInfoFile(): File {
        val timestamp = System.currentTimeMillis()
        val fileName = "gfxinfo_${target}_${timestamp}.txt"
        return File(resultDir, fileName).apply { createNewFile() }
    }

    private fun newDumpSysBatteryStatsFile(): File {
        val timestamp = System.currentTimeMillis()
        val fileName = "batterystats_${target}_${timestamp}.txt"
        return File(resultDir, fileName).apply { createNewFile() }
    }
}