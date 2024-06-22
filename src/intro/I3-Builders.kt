package intro

import kotlinx.coroutines.*
import shared.clients.ProductWebClient






suspend fun getProductType(product: String) = coroutineScope {
    ProductWebClient.getProductType(product)
}

fun main() {
    runBlocking {
        val deferredTypeIphone = async { getProductType("IPHONE") }
        val deferredTypeMacbook = async { getProductType("MACBOOK") }

        val typeIphone = deferredTypeIphone.await()
        val typeMacBook = deferredTypeMacbook.await()

        println(
            "IPhone is a $typeIphone " +
                    "and MacBook is a $typeMacBook"
        )
    }
}