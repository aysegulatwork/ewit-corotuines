package exercises

import kotlinx.coroutines.*
import shared.clients.PricingWebClient
import shared.clients.StockWebClient
import shared.log


/**
 *
 * Use the suspendable calculateOrder function
 * Stock service is taking too long to respond, cancel the call after 250 ms.
 * But we don't want our customer to be affected by this so our program should have a fallback mechanism where we assume that there is enough stock.
 *
 * 1. Catch the exception so the program won't fail.
 * 2. Calculate the total price anyway.
 *
 * extra: Introduce "finally" after try-catch and try delaying 1000 ms even though the job is cancelled.
 * hint: withContext(NonCancellable)
 */


fun main() {
    runBlocking {
        calculateOrderSusWebE5("IPHONE", 2)
    }
}


suspend fun calculateOrderSusWebE5(product: String, requestedQuantity: Int) = coroutineScope {
    val deferredStock = async {
        log("Calling stock")
        StockWebClient.getStock(product)
    }

    val deferredPrice = async {
        log("Calling pricing")
        PricingWebClient.getPrice(product)
    }

    delay(250)
    if (deferredStock.isActive){
        deferredStock.cancelAndJoin()
    }

    val stock =
        try {
            deferredStock.await()
        } catch (e: CancellationException){
            println("Error: ${e.message}")
            requestedQuantity
        }

    val price = deferredPrice.await()

    if (requestedQuantity <= stock) {
        val totalPrice = price * requestedQuantity
        println("Quantity=$requestedQuantity product=$product Total=$totalPrice")
        totalPrice
    } else {
        throw Exception("Insufficient stock to fulfill the request")
    }
}
