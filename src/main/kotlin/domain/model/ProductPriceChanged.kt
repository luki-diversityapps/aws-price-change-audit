package domain.model

import java.time.Instant

data class ProductPriceChanged(
    val eventId: EventId,
    val eventType: String,
    val eventVersion: Int,
    val occurredAt: Instant,
    val productId: ProductId,
    val oldPrice: Money,
    val newPrice: Money,
    val changedBy: String,
    val reason: String?
)