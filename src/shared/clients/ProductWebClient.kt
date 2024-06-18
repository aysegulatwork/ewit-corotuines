package shared.clients

import kotlinx.coroutines.delay
import shared.productsDatabase;

class ProductWebClient {
    companion object {
        suspend fun getProductType(product: String): String {
            delay(500) // Simulate IO delay, non-blocking
            return productsDatabase[product] ?: "Unknown"
        }
    }
}
