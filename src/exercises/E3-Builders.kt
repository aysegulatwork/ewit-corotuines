package exercises

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import shared.clients.StockRestClient
import shared.clients.PricingRestClient

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


fun calculateOrder(product: String, requestedQuantity: Int): Double {
    val stock = StockRestClient.getStock(product)
    val price = PricingRestClient.getPrice(product)

    if (requestedQuantity <= stock) {
        val totalPrice = price * requestedQuantity
        println("Quantity=$requestedQuantity product=$product Total=$totalPrice")
        return totalPrice
    } else {
        throw Exception("Insufficient stock to fulfill the request")
    }
}


fun main() {
    calculateOrder("IPHONE", 2)
}