package exercises

import kotlinx.coroutines.*
import shared.clients.PricingRestClient
import shared.clients.PricingWebClient
import shared.clients.StockRestClient
import shared.clients.StockWebClient
import shared.executeAndMeasureTime
import shared.log

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
    executeAndMeasureTime("First example using Rest client"){
        runBlocking {   calculateOrderSus("IPHONE", 2)  }
    }

    executeAndMeasureTime("First example using Web client"){
        runBlocking { calculateOrderSusWeb("IPHONE", 2)  }
    }

    executeAndMeasureTime("First example using Rest client with IO Dispatcher"){
        runBlocking(Dispatchers.IO) {   calculateOrderSus("IPHONE", 2)  }
    }

    executeAndMeasureTime("First example using Web client with Default Dispatcher"){
        runBlocking(Dispatchers.Default) { calculateOrderSusWeb("IPHONE", 2)  }
    }
}


suspend fun calculateOrderSusWeb(product: String, requestedQuantity: Int) = coroutineScope {
    val deferredStock = async {
        log("Calling stock")
        StockWebClient.getStock(product)
    }

    val deferredPrice = async {
        log("Calling pricing")
        PricingWebClient.getPrice(product)
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

suspend fun calculateOrderSus(product: String, requestedQuantity: Int) = coroutineScope {
    val deferredStock = async {
        log("Calling stock")
        StockRestClient.getStock(product)
    }
    val deferredPrice = async {
        log("Calling pricing")
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