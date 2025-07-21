import kotlinx.coroutines.*

fun main() {
    runBlocking {
        println("${Thread.currentThread().name} - runBlocking function")
                launch {
            println("${Thread.currentThread().name} - launch function")
            withContext(Dispatchers.Default) {
                println("${Thread.currentThread().name} - withContext function")
                delay(1000)
                println("10 results found.")
            }
            println("${Thread.currentThread().name} - end of launch function")
        }
        println("Loading...")
    }
}
suspend fun getWeatherReport() = coroutineScope {
    val forecast = async { getForecast() }
    val temperature = async { getTemperature() }
    delay(200)
    temperature.cancel()

    "${forecast.await()}"
}

suspend fun getForecast(): String {
    delay(1000)
    return "Sunny"
}

suspend fun getTemperature(): String {
    delay(1000)
    return "30\u00b0C"
}
/*you can observe that most of the code is executed in coroutines on the main thread. 
 * However, for the portion of your code in the withContext(Dispatchers.Default) block, that is executed in a coroutine on a Default Dispatcher worker thread (which is not the main thread).
 *  Notice that after withContext() returns, the coroutine returns to running on the main thread (as evidenced by output statement: main @coroutine#2 - end of launch function). 
 * This example demonstrates that you can switch the dispatcher by modifying the context that is used for the coroutine.
 * 
 * https://developer.android.com/codelabs/basic-android-kotlin-compose-coroutines-kotlin-playground?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-compose-unit-5-pathway-1%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-compose-coroutines-kotlin-playground#4
 * */
