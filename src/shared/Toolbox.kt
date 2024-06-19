package shared

import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext
import kotlin.system.measureTimeMillis

const val boldStart = "\u001B[1m"
const val boldEnd = "\u001B[0m"

//fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

@OptIn(ExperimentalStdlibApi::class)
suspend fun log(msg: String) =
    println("[Context:${coroutineContext[CoroutineDispatcher]}] [Thread: ${Thread.currentThread().name}] $msg")

fun <T> measureTime(msg: String, fn: () -> T) {
    printWithDashes(msg)
    val timeTaken = measureTimeMillis {
        fn()
    }
    println("${boldStart}Time${boldEnd}: $timeTaken ms")
}

fun printWithDashes(msg: String, totalLength: Int = 100) {
    val dashesCount = totalLength - msg.length
    val dashes = "-".repeat(dashesCount.coerceAtLeast(0))
    println("${boldStart}$msg${boldEnd}$dashes")
}

/*
fun printWithDashes(msg: String, totalLength: Int = 60, colorCode: String = "\u001B[32m") {
    val resetCode = "\u001B[0m"
    val dashesCount = totalLength - msg.length
    val dashes = "-".repeat(dashesCount.coerceAtLeast(0))
    println("$colorCode$msg$resetCode$dashes")
}

// Example usage:
fun main() {
    val message = "Hello, World!"
    printWithDashes(message) // Default green color
    printWithDashes("Short", colorCode = "\u001B[34m") // Blue color
    printWithDashes("A bit longer message", colorCode = "\u001B[31m") // Red color
    printWithDashes("This is a really, really, really, really long message that will likely exceed the total length limit", colorCode = "\u001B[33m") // Yellow color
}

*/