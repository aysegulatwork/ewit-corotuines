package intro

import kotlinx.coroutines.*

fun main() {
//    runBlocking {
//        coroutineScope {
//            launch {
//                delay(2000)
//                println("Will I be printed?")
//            }
//
//            launch {
//                throw Exception("I want to break free!")
//            }
//
//            delay(2000)
//            println("Done!")
//        }
//    }

    runBlocking {
        supervisorScope {
            launch {
                delay(2000)
                println("Will I be printed?")
            }

            launch {
                throw Exception("I want to break free!")
            }

            delay(2000)
            println("Done!")
        }
    }
}



