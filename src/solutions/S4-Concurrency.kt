@file:Suppress("DuplicatedCode")

package solutions

import kotlinx.coroutines.*
import shared.clients.*
import shared.log
import shared.measureTime

/**
 * 1. Observe the two client calls we made in the previous exercise, are they really parallel?
 *
 * 2. How do we make them parallel?
 *
 * 3. Better yet, how do we make them concurrent? (TIP: use asynchronous clients)
 *
 * Example: Spring MVC vs Spring WebFlux
 *
 */


fun main() {
    measureTime("Normal function with REST call") {
        calculateOrder("IPHONE", 2)
    }

    measureTime("Suspendable function with REST call") {
        runBlocking {
            calculateOrderSus(product = "IPHONE", requestedQuantity = 2)
        }
    }

    measureTime("IO Dispatcher - Suspendable function with REST call") {
        runBlocking(Dispatchers.IO) {
            calculateOrderSus(product = "IPHONE", requestedQuantity = 2)
        }
    }

    measureTime("Default Dispatcher- Suspendable function with WEBCLIENT call") {
        runBlocking(Dispatchers.Default) {
            calculateOrderSusAsync(product = "IPHONE", requestedQuantity = 2)
        }
    }


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

suspend fun calculateOrderSusAsync(product: String, requestedQuantity: Int) = coroutineScope {
    val deferredStock = async {
        log("Calling STOCK service")
        StockWebClient.getStock(product)
    }

    val deferredPrice = async {
        log("Calling PRICING service")
        PricingWebClient.getPrice(product)
    }

    val stock = deferredStock.await()
    val price = deferredPrice.await()

    if (requestedQuantity <= stock) {
        val totalPrice = price * requestedQuantity
        println("Quantity=$requestedQuantity product=$product Total=$totalPrice")
        totalPrice

    } else {
        throw Exception("Insufficient stock to fulfill the request for $product")
    }
}
