enum class Target(val inputArg: String, val testName: String, val packageName: String) {
    ANDROID_NATIVE("android-native", "AndroidNativeTest", "com.example.android_app"),
    ANDROID_FLUTTER("android-flutter", "AndroidFlutterTest", "com.example.flutter_app"),
    ANDROID_IONIC("android-ionic", "AndroidIonicTest", "io.ionic.starter");

    companion object {
        fun byInputArg(inputArg: String): Target {
            return when (inputArg) {
                ANDROID_NATIVE.inputArg -> ANDROID_NATIVE
                ANDROID_FLUTTER.inputArg -> ANDROID_FLUTTER
                ANDROID_IONIC.inputArg -> ANDROID_IONIC
                else -> throw IllegalArgumentException("No matching target for: $inputArg")
            }
        }
    }
}