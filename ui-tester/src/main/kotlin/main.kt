import java.io.File

const val TYPE_ANDROID = "android"

const val WEB_API_PATH = "/Users/admin/Desktop/cross-platform-benchmark/bin/web-api-0.0.1-SNAPSHOT.jar"

lateinit var webApiProcess: Process

fun main(args: Array<String>) {
    println("START")

    if (args.isEmpty()) {
        println("no arguments passed")
    } else {
        runSelectedTest(type = args[0])
    }

    println("END")
}

private fun runSelectedTest(type: String) {
    when (type) {
        TYPE_ANDROID -> runTest(AndroidTest(), type)
        else -> println("invalid type: $type")
    }
}

private fun runTest(test: Test, type: String) {
    println("start $type test")
    test.run()
    println("$type test finished")
}

private fun runWebApi() {
    println("start web-api")
    webApiProcess = ProcessBuilder("java", "-jar", WEB_API_PATH)
        .directory(File(WEB_API_PATH))
        .start()
    println("web-api started")
}

private fun stopWebApi() {
    println("stop web-api")
    webApiProcess.destroy()
    println("web-api stopped")
}