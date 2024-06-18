package shared.clients

import kotlinx.coroutines.delay
import shared.pricesDatabase

class PricingWebClient {
    companion object {
        suspend fun getPrice(product: String): Double {
            delay(500) // Simulate IO delay, non-blocking
            return pricesDatabase.getOrDefault(product, 0.0)
        }
    }
}