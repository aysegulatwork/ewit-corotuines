package shared

import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext
import kotlin.system.measureTimeMillis

const val boldStart = "\u001B[1m"
const val boldEnd = "\u001B[0m"

const val blue = "\u001B[34m"
const val red = "\u001B[31m"
const val green = "\u001B[32m"
const val black = "\u001B[30m"



@OptIn(ExperimentalStdlibApi::class)
suspend fun log(msg: String) {
    println("$blue[Context:${coroutineContext[CoroutineDispatcher]}] $red[Thread: ${Thread.currentThread().name}] $black$msg")
}

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