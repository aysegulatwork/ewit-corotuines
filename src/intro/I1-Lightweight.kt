package intro

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * The difference between coroutines and threads, coroutines are lightweight!
 * 1 GB memory holds up to 4096 kernel threads which are directly linked to JVM threads.
 * 1 GB memory can handle up to 2,4 million coroutines (depending on the task they are running) and handled by JVM threads.
 * Good read: https://medium.com/swlh/coroutines-pilove-notes-cb83654a88d4
 *
 *
 * How does Thread.sleep() work?
 * How does delay() work?
 *
 * */


fun main() {
    println("Threads")

    val threads = measureTimeMillis {
        repeat(50) { // block a lot of threads
            Thread.sleep(200) // blocking
            print("-")
        }
    }

    println("\nCoroutines")

    val coroutines = runBlocking {
        measureTimeMillis {
            repeat(50) { // launch a lot of coroutines
                launch {
                    delay(200) // suspendable
                    print("-")
                }
            }
        }
    }

    println("\nThreads are ${(threads / coroutines)} times slower than coroutines.")
}


