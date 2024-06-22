package intro

import kotlinx.coroutines.*
import shared.clients.ProductRestClient
import shared.clients.ProductWebClient
import shared.log
import shared.measureTime
import kotlin.system.measureTimeMillis

/**
 * 1. Observe the two calls we are making below, are they parallel?
 *
 * 2. How do we make them parallel?
 *
 * 3. How to make them concurrent?
 *
 */

fun main() {
    runBlocking {
        launch {
            log("I am getting product type for Iphone")
//                ProductRestClient.getProductType("IPHONE")
            ProductWebClient.getProductType("IPHONE")
        }

        launch {
            log("I am getting product type for Macbook")
//                ProductRestClient.getProductType("MACBOOK")
            ProductWebClient.getProductType("MACBOOK")
        }
    }
}