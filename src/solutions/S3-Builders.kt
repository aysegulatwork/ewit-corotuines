package solutions


import shared.clients.StockRestClient
import shared.clients.PricingRestClient
import kotlinx.coroutines.*


/**
 * Customer added two telephones (IPHONE) to their cart.
 * They now want to check out and go to payments step.
 * Stock and pricing might have changed while customer is browsing in the website.
 *
 * calculateOrder function checks the up-to-date availability and computes the total value of a given product.
 * Make this function suspendable and make the stock and price calls in parallel, using coroutines.
 *
 * Calculate the up-to-date total price and print it.
 **/

suspend fun calculateOrderSus(product: String, requestedQuantity: Int) = coroutineScope {
    val deferredStock = async { StockRestClient.getStock(product) }
    val deferredPrice = async { PricingRestClient.getPrice(product) }

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


fun calculateOrder(product: String, requestedQuantity: Int): Double {
    val availableStock = StockRestClient.getStock(product)
    val price = PricingRestClient.getPrice(product)

    return if (requestedQuantity <= availableStock) {
        val totalPrice = price * requestedQuantity
        println("Quantity=$requestedQuantity product=$product Total=$totalPrice")
        totalPrice
    } else {
        throw Exception("Insufficient stock to fulfill the request")
    }
}

fun main() {
    calculateOrder("IPHONE", 2)

    runBlocking { calculateOrderSus(product = "IPHONE", requestedQuantity = 2) }
}
