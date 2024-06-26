package exercises

import kotlinx.coroutines.*
import solutions.calculateOrderSusWebclient

/**
 * While the customer was trying to buy 100 IPhones and 10 MacBooks, IPhone went out of stock.
 * We do not want to fail whole basket/checkout calculation, we want customer to be able to order the MacBook.
 * Use calculateOrderSusWebclient from previous exercise
 *
 */

fun main() {
    runBlocking(Dispatchers.Default) {
        coroutineScope {
            launch { calculateOrderSusWebclient("IPHONE", 100) }
            delay(1000) //to make sure that we see the exception being thrown first
            launch { calculateOrderSusWebclient("MACBOOK", 10) }
        }
    }
}