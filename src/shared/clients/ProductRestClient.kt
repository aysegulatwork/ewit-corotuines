package shared.clients

import shared.productsDatabase;

class ProductRestClient {
    companion object {
        fun getProductType(product: String): String {
            Thread.sleep(500) // Simulate IO delay
            return productsDatabase[product] ?: "Unknown"
        }
    }
}
