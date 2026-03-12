package application.submit

import domain.model.PriceEventType
import domain.model.ProductPriceChanged
import domain.valueobject.EventId
import port.submit.PriceChangeEventPublisher
import port.submit.SubmitPriceChangeUseCase
import java.time.Clock
import java.time.Instant

class SubmitPriceChangeHandler(
    private val priceChangeEventPublisher: PriceChangeEventPublisher,
    private val clock: Clock = Clock.systemUTC(),
) : SubmitPriceChangeUseCase {
    override fun handle(command: SubmitPriceChangeCommand) {
        priceChangeEventPublisher.publish(
            ProductPriceChanged(
                eventId = EventId.random(),
                eventType = PriceEventType.PRODUCT_PRICE_CHANGED,
                eventVersion = ProductPriceChanged.VERSION,
                occurredAt = Instant.now(clock),
                productId = command.productId,
                oldPrice = command.oldPrice,
                newPrice = command.newPrice,
                changedBy = command.changedBy,
                reason = command.reason,
            ),
        )
    }
}
