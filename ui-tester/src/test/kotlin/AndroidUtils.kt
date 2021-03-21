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
        val outputFile = newVmStatFile()
        vmStat = ProcessBuilder()
            .redirectOutput(outputFile)
            .command("adb", "shell", "vmstat", "1", monitoringTimeSeconds.toString())
            .start()
    }

    fun stopVmStat() {
        vmStat.destroy()
    }

    fun dumpSysGfxInfo() {
        val outputFile = newDumpSysGfxInfo()
        ProcessBuilder()
            .redirectOutput(outputFile)
            .command("adb", "shell", "dumpsys", "gfxinfo", packageName)
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

    private fun newDumpSysGfxInfo(): File {
        val timestamp = System.currentTimeMillis()
        val fileName = "gfxinfo_${target}_${timestamp}.txt"
        return File(resultDir, fileName).apply { createNewFile() }
    }
}