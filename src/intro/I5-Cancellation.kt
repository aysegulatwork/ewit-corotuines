package intro

import kotlinx.coroutines.*
import java.lang.Thread.sleep
import kotlin.concurrent.thread


fun main() {

    /***** Thread *******/
    val thread = thread {
        try {
            sleep(5000)
        } catch (e: InterruptedException) {
            println("Thread was interrupted during sleep")
        }
    }

    sleep(1000)
    thread.interrupt()
    println("Thread: Main program is finished anyway.")






    /***** Coroutine *******/
    runBlocking {
        val job = launch {
            try {
                delay(5000)
            } catch (e: CancellationException) {
                println("Coroutine was cancelled during sleep")
            }
        }
        delay(1000)
        job.cancelAndJoin()
        println("Coroutine: Main program is finished anyway.")
    }
}
