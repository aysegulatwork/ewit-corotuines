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
}