package shared.clients

import shared.pricesDatabase

class PricingRestClient {
    companion object {
        fun getPrice(product: String): Double {
            Thread.sleep(500) // Simulate IO delay, blocking
            return pricesDatabase.getOrDefault(product, 0.0)
        }
    }
}