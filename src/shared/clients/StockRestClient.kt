package shared.clients

import shared.stockDatabase


class StockRestClient {
    companion object {
        fun getStock(product: String): Int {
            Thread.sleep(500) // Simulate IO delay
            return stockDatabase[product] ?: 0
        }
    }
}