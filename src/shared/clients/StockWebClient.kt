package shared.clients

import kotlinx.coroutines.delay
import shared.stockDatabase


class StockWebClient {
    companion object {
        suspend fun getStock(productId: String): Int {
            delay(500)  // Simulate IO delay, non-blocking
            return stockDatabase[productId] ?: 0
        }
    }
}