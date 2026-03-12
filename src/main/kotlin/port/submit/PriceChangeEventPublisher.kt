package port.submit

import domain.model.ProductPriceChanged

fun interface PriceChangeEventPublisher {
    fun publish(event: ProductPriceChanged)
}
