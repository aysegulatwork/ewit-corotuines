package exercises

import kotlinx.coroutines.*
import shared.clients.PricingRestClient
import shared.clients.PricingWebClient
import shared.clients.StockRestClient
import shared.clients.StockWebClient
import shared.log


/**
 * Stock service is taking too long to respond, cancel the call after 250 ms.
 * But we don't want our customer to be affected by this so our program should have a fallback mechanism where we assume that there is enough stock.
 *
 * 1. Catch the exception so the program won't fail.
 * 2. Calculate the total price anyway.
 *
 */



private suspend fun calculateOrderSus(product: String, requestedQuantity: Int) = coroutineScope {
    val deferredStock = async { StockWebClient.getStock(product) }
    val deferredPrice = async { PricingWebClient.getPrice(product) }

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






fun main() {
    runBlocking {
        calculateOrderSus("IPHONE", 2)
    }
}