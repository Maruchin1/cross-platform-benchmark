object Config {
    const val OPENED_EVENTS = 2
    const val SCROLLS_BETWEEN_OPENED_EVENTS = 3

    const val INITIAL_TIME = 2_000L
    const val READING_TIME = 2_000L
    const val SCROLL_INTERVAL = 1_000L
    const val SCROLL_DURATION = 500L

    fun calculateExecutionTimeSeconds(): Long {
        val millis =
            INITIAL_TIME + OPENED_EVENTS * (SCROLLS_BETWEEN_OPENED_EVENTS * (SCROLL_DURATION + SCROLL_INTERVAL) + READING_TIME)
        return millis / 1_000
    }
}