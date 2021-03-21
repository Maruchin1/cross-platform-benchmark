import java.io.File

class ResultsStorage {

    private val resultDir: File by lazy {
        File("results").apply { if (!exists()) mkdir() }
    }

    fun newVmStatFile(target: Target): File {
        val timestamp = System.currentTimeMillis()
        val fileName = "vmstat_${target}_${timestamp}.txt"
        return File(resultDir, fileName).apply { createNewFile() }
    }
}