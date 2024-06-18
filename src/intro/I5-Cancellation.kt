package intro

import kotlinx.coroutines.*
import java.lang.Thread.sleep
import kotlin.concurrent.thread


fun main() {
    /***** Thread *******/
    val thread = thread {
        try {
            println("Thread is going to sleep")
            sleep(5000)
            println("Sleep is finished")
        } catch (e: InterruptedException) {
            println("Thread was interrupted during sleep")
        }
    }
    // Wait for a short while before interrupting the thread
    sleep(1000)
    println("Main thread: Interrupting the thread...")
    thread.interrupt()
    println("Main thread is finished...")


    /***** Coroutine *******/
    runBlocking {
        val job = launch {
            try {
                println("Coroutine is going to sleep")
                delay(5000)
                println("Sleep is finished")
            } catch (e: CancellationException) {
                println("Coroutine was cancelled during sleep")
            }
        }
        delay(1000)
        println("Main thread: Cancelling the coroutine...")
        job.cancelAndJoin()
        println("Main thread is finished")
    }
}
