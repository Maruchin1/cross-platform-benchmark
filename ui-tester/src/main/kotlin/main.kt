import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val target = Target.byInputArg(args[0])
    val terminal = Terminal()
    val resultsStorage = ResultsStorage()

    val vmStat = terminal.startVmStat(
        outputFile = resultsStorage.newVmStatFile(target),
        monitoringTimeSeconds = Config.calculateExecutionTimeSeconds()
    )

    val uiTest = terminal.startUiTest(target)

    uiTest.waitFor()
    vmStat.waitFor()

    val uninstallApp = terminal.uninstallApp(target)
    uninstallApp.waitFor()

    exitProcess(0)
}