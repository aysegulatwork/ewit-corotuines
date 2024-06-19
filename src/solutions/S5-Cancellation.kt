@file:Suppress("DuplicatedCode")

package solutions

import kotlinx.coroutines.*
import shared.clients.PricingWebClient
import shared.clients.StockWebClient
import shared.measureTime

/**
 *
 * Use the suspendable calculateOrder function
 * Stock service is taking too long to respond, cancel the call after 250 ms.
 * But we don't want our customer to be affected by this so our program should have a fallback mechanism where we assume that there is enough stock.
 *
 * 1. Cancel the stock service call
 * 2. Calculate the total price anyway.
 * 3. Do we want to wait for cancellation to finish?
 *
 * extra: Introduce "finally" after try-catch and try delaying 1000 ms even though the job is cancelled.
 * hint: withContext(NonCancellable)
*/


fun main() {
    measureTime("Cancellation exercise"){
        runBlocking {
            calculateOrderCancellable("IPHONE", 2)
        }
    }
}

suspend fun calculateOrderCancellable(product: String, requestedQuantity: Int) = coroutineScope {
    val deferredStock = async { StockWebClient.getStock(product) }
    val deferredPrice = async { PricingWebClient.getPrice(product) }

    delay(250)
    if (deferredStock.isActive){
        deferredStock.cancel()
//        deferredStock.cancelAndJoin()
    }

    val stock = deferredStock.await()

//    val stock = try {
//        deferredStock.await()
//    }  catch (e: CancellationException){
//        requestedQuantity
//    }

    val price = deferredPrice.await()

    if (requestedQuantity <= stock) {
        val totalPrice = price * requestedQuantity
        println("Quantity=$requestedQuantity product=$product Total=$totalPrice")
        totalPrice
    } else {
        throw Exception("Insufficient stock to fulfill the request")
    }
}

