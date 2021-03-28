package android_framework

import java.io.File

class AndroidDataExtractor(
    private val target: String
) {
    companion object {
        const val SEPARATOR = ", "
    }

    private val resultFile: File by lazy {
        val timestamp = System.currentTimeMillis()
        val dir = File("../analysis/results").also { if (!it.exists()) it.mkdir() }
        File(dir, "${timestamp}_${target}.csv").also {
            it.createNewFile()
            it.writeText(makeResultHeader())
        }
    }

    val cpuStatsFile: File by lazy { createTempFile() }
    val frameStatsFile: File by lazy { createTempFile() }
    val ramStatsFile: File by lazy { createTempFile() }
    val batteryStatsFile: File by lazy { createTempFile() }

    fun extractDataAndSave() {
        val (minCpu, avgCpu, maxCpu) = getCpuUsage()
        val (minRam, avgRam, maxRam) = getRamUsage()
        val data = DataModel(
            batteryDischargePercent = getBatteryDischarge(),
            jankyFramesPercent = getJankyFrames(),
            minCpuUsage = minCpu,
            avgCpuUsage = avgCpu,
            maxCpuUsage = maxCpu,
            minRamAlloc = minRam,
            avgRamAlloc = avgRam,
            maxRamAlloc = maxRam
        )
        resultFile.appendText(data.toResultLine())
    }

    private fun getBatteryDischarge(): Double {
        val lines = batteryStatsFile.readLines()
            .asSequence()
            .map { it.trim() }
        val capacity = lines
            .find { it.startsWith("Estimated battery capacity:") }!!
            .substringAfter("capacity: ")
            .substringBefore(" mAh")
            .toDouble()
        val discharge = lines
            .find { it.startsWith("Discharge:") }!!
            .substringAfter("Discharge: ")
            .substringBefore(" mAh")
            .toDouble()
        return (discharge / capacity) * 100
    }

    private fun getJankyFrames(): Double {
        val lines = frameStatsFile.readLines()
            .asSequence()
            .map { it.trim() }
        val totalFrames = lines
            .find { it.startsWith("Total frames rendered:") }!!
            .substringAfter("rendered: ")
            .substringBefore("\n")
            .toDouble()
        val jankyFrames = lines
            .find { it.startsWith("Janky frames:") }!!
            .substringAfter("frames: ")
            .substringBefore(" ")
            .toDouble()
        return (jankyFrames / totalFrames) * 100
    }

    private fun getCpuUsage(): Triple<Double, Double, Double> {
        return cpuStatsFile.readLines()
            .asSequence()
            .drop(2)
            .map { it.trim() }
            .map { it.split("\\s+".toRegex()) }
            .map { 100 - it[14].toInt() }
            .map { it.toDouble() }
            .let { Triple(it.minOrNull()!!, it.average(), it.maxOrNull()!!) }
    }

    private fun getRamUsage(): Triple<Double, Double, Double> {
        return ramStatsFile.readLines()
            .asSequence()
            .map { it.trim() }
            .find { it.startsWith("TOTAL:") }!!
            .substringAfter("/")
            .substringBefore("/")
            .replace("MB", "")
            .split("-")
            .map { it.toDouble() }
            .let { Triple(it[0], it[1], it[2]) }
    }

    private fun makeResultHeader(): String = buildString {
        append("batteryDischargePercent")
        append(SEPARATOR)
        append("jankyFramesPercent")
        append(SEPARATOR)
        append("minCpuUsage")
        append(SEPARATOR)
        append("avgCpuUsage")
        append(SEPARATOR)
        append("maxCpuUsage")
        append(SEPARATOR)
        append("minRamAlloc")
        append(SEPARATOR)
        append("avgRamAlloc")
        append(SEPARATOR)
        append("maxRamAlloc")
    }

    data class DataModel(
        val batteryDischargePercent: Double,
        val jankyFramesPercent: Double,
        val minCpuUsage: Double,
        val avgCpuUsage: Double,
        val maxCpuUsage: Double,
        val minRamAlloc: Double,
        val avgRamAlloc: Double,
        val maxRamAlloc: Double
    ) {
        fun toResultLine(): String = buildString {
            appendLine()
            append(batteryDischargePercent)
            append(SEPARATOR)
            append(jankyFramesPercent)
            append(SEPARATOR)
            append(minCpuUsage)
            append(SEPARATOR)
            append(avgCpuUsage)
            append(SEPARATOR)
            append(maxCpuUsage)
            append(SEPARATOR)
            append(minRamAlloc)
            append(SEPARATOR)
            append(avgRamAlloc)
            append(SEPARATOR)
            append(maxRamAlloc)
        }

    }
}