package exercises

import kotlinx.coroutines.*
import shared.clients.PricingRestClient
import shared.clients.PricingWebClient
import shared.clients.StockRestClient
import shared.clients.StockWebClient
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
    measureTime("Blocking function with REST call") {
        calculateOrder("IPHONE", 2)
    }

    measureTime("Suspendable function with REST call") {
        runBlocking {
            calculateOrderSus(product = "IPHONE", requestedQuantity = 2)
        }
    }
}











private suspend fun calculateOrderSus(product: String, requestedQuantity: Int) = coroutineScope {
    val deferredStock = async {
        log("Calling STOCK service")
        StockRestClient.getStock(product)
    }

    val deferredPrice = async {
        log("Calling PRICING service")
        PricingRestClient.getPrice(product)
    }

    val stock = deferredStock.await()
    val price = deferredPrice.await()

    if (requestedQuantity <= stock) {
        val totalPrice = price * requestedQuantity
        println("Quantity=$requestedQuantity product=$product Total=$totalPrice")
        totalPrice
    } else {
        throw Exception("Insufficient stock to fulfill the request")
    }
}