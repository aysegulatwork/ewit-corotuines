package intro

import kotlinx.coroutines.*
import shared.clients.ProductWebClient

fun main() {
    runBlocking {
        val deferredTypeIphone = async { getProductType("IPHONE") }
        val deferredTypeMacbook = async { getProductType("MACBOOK") }

        println(
            "IPhone is a ${deferredTypeIphone.await()} " +
                    "and MacBook is a ${deferredTypeMacbook.await()}"
        )
    }
}

suspend fun getProductType(product: String) = coroutineScope {
    ProductWebClient.getProductType(product)
}