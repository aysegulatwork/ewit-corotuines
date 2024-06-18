package intro

import kotlinx.coroutines.*

fun main() {
    runBlocking {
        launch {
            println("Hallo,")
        }

        launch {
            println("Wereld!")
        }
    }
}
