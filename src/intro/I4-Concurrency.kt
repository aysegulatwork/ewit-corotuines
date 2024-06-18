package intro

import kotlinx.coroutines.*
import shared.clients.ProductRestClient
import shared.clients.ProductWebClient
import shared.log
import kotlin.system.measureTimeMillis

/**
 * 1. Observe the two calls we are making below, are they parallel?
 *
 * 2. How do we make them parallel?
 *
 * 3. How to make them concurrent?
 *
 */

@OptIn(ExperimentalStdlibApi::class)
fun main() {
    val time = measureTimeMillis {
        runBlocking(Dispatchers.Default) {
            launch {
                log("I am getting product type for Iphone")
//                ProductRestClient.getProductType("IPHONE")
                ProductWebClient.getProductType("IPHONE")
            }

            launch {
                log("I am getting product type for Macbook")
//                ProductRestClient.getProductType("MACBOOK")
                ProductWebClient.getProductType("MACBOOK")
            }
        }
    }

    println("Took ${time}ms")


//    /************* Dispatcher.Unconfined ************/
//    runBlocking(Dispatchers.Unconfined) {
//        log("This is before suspending")
//        delay(1000)
//        log("This is after suspending")
//    }


    /************* Own Thread ************/
//    runBlocking {
//        launch(newSingleThreadContext("MyOwnThread")) {
//            log("This is my own thread")
//        }
//    }
}