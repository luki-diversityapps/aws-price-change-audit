package io.github.luki84.pricechangeaudit.application.pricechange.submit

import io.github.luki84.pricechangeaudit.application.port.inbound.SubmitPriceChangeUseCase
import io.github.luki84.pricechangeaudit.application.port.outbound.pricechange.PublishPriceChangeEventPort
import io.github.luki84.pricechangeaudit.application.port.outbound.time.CurrentTimePort
import io.github.luki84.pricechangeaudit.domain.model.pricechange.EventId
import io.github.luki84.pricechangeaudit.domain.model.pricechange.PriceChanged

class SubmitPriceChangeHandler(
    private val publishPriceChangeEventPort: PublishPriceChangeEventPort,
    private val currentTimePort: CurrentTimePort,
) : SubmitPriceChangeUseCase {
    override fun handle(command: SubmitPriceChangeCommand) {
        publishPriceChangeEventPort.publish(
            PriceChanged(
                eventId = EventId.random(),
                eventVersion = PriceChanged.VERSION,
                occurredAt = currentTimePort.now(),
                productId = command.productId,
                oldPrice = command.oldPrice,
                newPrice = command.newPrice,
                changedBy = command.changedBy,
                reason = command.reason,
            ),
        )
    }
}
