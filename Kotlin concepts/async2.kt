import kotlinx.coroutines.*

fun main() {
    runBlocking {
        println("Weather forecast")
        println(getWeatherReport())
        println("Have a good day!")
    }
}
//coroutineScope{} creates a local scope for this weather report task. 
//The coroutines launched within this scope are grouped together within this scope, which has implications for cancellation and exceptions 
suspend fun getWeatherReport() = coroutineScope {
    val forecast = async { getForecast() }
    val temperature = async { getTemperature() }
    "${forecast.await()} ${temperature.await()}"
}

suspend fun getForecast(): String {
    delay(1000)
    return "Sunny"
}

suspend fun getTemperature(): String {
    delay(1000)
    return "30\u00b0C"
}

/* CoroutineScope() will only return once all its work, including any coroutines it launched, have completed. In this case, both coroutines getForecast() and getTemperature() need to finish and return their respective results. 
 * Then the Sunny text and 30°C are combined and returned from the scope. This weather report of Sunny 30°C gets printed to the output, and the caller can proceed to the last print statement of Have a good day!.
 * With coroutineScope(), even though the function is internally doing work concurrently, it appears to the caller as a synchronous operation because coroutineScope won't return until all work is done.
 * The key insight here for structured concurrency is that you can take multiple concurrent operations and put it into a single synchronous operation, where concurrency is an implementation detail. 
 * The only requirement on the calling code is to be in a suspend function or coroutine. Other than that, the structure of the calling code doesn't need to take into account the concurrency details.
 * */