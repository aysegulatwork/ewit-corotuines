package intro

import kotlinx.coroutines.*
import shared.clients.ProductRestClient
import shared.clients.ProductWebClient
import shared.log
import shared.printWithDashes

fun main() {


    printWithDashes("FOR BLOCKING OPERATIONS - ONLY PARALLELISM")

    runBlocking(Dispatchers.IO) {
        launch {
            log("Rest call for IPHONE")
            ProductRestClient.getProductType("IPHONE")
        }

        launch {
            log("Rest call for MACBOOK")
            ProductRestClient.getProductType("MACBOOK")
        }
    }












    printWithDashes("FOR NON-BLOCKING OPERATIONS - CONCURRENCY&PARALLELISM")

    runBlocking(Dispatchers.Default) {
        launch {
            log("WebClient call for IPHONE")
            ProductWebClient.getProductType("IPHONE")
        }

        launch {
            log("WebClient call for MACBOOK")
            ProductWebClient.getProductType("MACBOOK")
        }
    }


}