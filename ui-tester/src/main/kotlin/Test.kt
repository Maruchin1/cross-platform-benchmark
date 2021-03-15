abstract class Test {

    fun run() {
        setup()
        test()
        clear()
    }

    protected abstract fun setup()

    protected abstract fun test()

    protected abstract fun clear()
}