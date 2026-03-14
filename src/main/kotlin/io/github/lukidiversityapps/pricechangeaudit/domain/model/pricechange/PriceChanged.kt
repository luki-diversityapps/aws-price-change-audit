package io.github.lukidiversityapps.pricechangeaudit.domain.model.pricechange

import io.github.lukidiversityapps.pricechangeaudit.domain.model.common.UserId
import io.github.lukidiversityapps.pricechangeaudit.domain.model.money.Money
import java.time.Instant

data class PriceChanged(
    val eventId: EventId,
    val occurredAt: Instant,
    val productId: ProductId,
    val oldPrice: Money,
    val newPrice: Money,
    val changedBy: UserId,
    val reason: PriceChangeReason?,
) {
    val eventVersion: Int = VERSION

    companion object {
        const val VERSION = 1
    }
}
