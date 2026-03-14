package io.github.lukidiversityapps.pricechangeaudit.application.port.outbound.pricechange

import io.github.lukidiversityapps.pricechangeaudit.domain.model.pricechange.ProductPriceChanged

fun interface PublishPriceChangeEventPort {
    fun publish(event: ProductPriceChanged)
}
