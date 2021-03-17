const val NATIVE_ANDROID = "native-android"

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
        NATIVE_ANDROID -> runTest(AndroidTest(), type)
        else -> println("invalid type: $type")
    }
}

private fun runTest(test: Test, type: String) {
    println("start $type test")
    test.run()
    println("$type test finished")
}