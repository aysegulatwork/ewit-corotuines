package exercises

import kotlinx.coroutines.*

fun main() {
    // TODO implement hello, world! using by using coroutines

    runBlocking {
        launch {
            delay(1000L)
            print("Hello,")
        }

        launch {
            println("World")
        }
    }

}
