@file:Suppress("DuplicatedCode")

package solutions

import kotlinx.coroutines.*
import shared.clients.*
import shared.executeAndMeasureTime

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
    executeAndMeasureTime("Normal function with sync client") {
        calculateOrder("IPHONE", 2)
    }

    executeAndMeasureTime("Suspendable function with sync client") {
        runBlocking {
            calculateOrderSus(product = "IPHONE", requestedQuantity = 2)
        }
    }

    executeAndMeasureTime("Suspendable function with sync client, IO Dispatcher") {
        runBlocking(Dispatchers.IO) {
            calculateOrderSus(product = "IPHONE", requestedQuantity = 2)
        }
    }

    executeAndMeasureTime("Suspendable function with async, Default Dispatcher, async") {
        runBlocking(Dispatchers.Default) {
            calculateOrderSusAsync(product = "IPHONE", requestedQuantity = 2)
        }
    }

    //TODO extras

}

suspend fun calculateOrderSusAsync(product: String, requestedQuantity: Int) = coroutineScope {
    val deferredStock = async { StockWebClient.getStock(product) }
    val deferredPrice = async { PricingWebClient.getPrice(product) }

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
