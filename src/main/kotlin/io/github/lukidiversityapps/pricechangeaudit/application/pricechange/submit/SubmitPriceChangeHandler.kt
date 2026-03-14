package io.github.lukidiversityapps.pricechangeaudit.application.pricechange.submit

import io.github.lukidiversityapps.pricechangeaudit.application.port.inbound.SubmitPriceChangeUseCase
import io.github.lukidiversityapps.pricechangeaudit.application.port.outbound.pricechange.PublishPriceChangeEventPort
import io.github.lukidiversityapps.pricechangeaudit.application.port.outbound.time.CurrentTimePort
import io.github.lukidiversityapps.pricechangeaudit.domain.model.pricechange.EventId
import io.github.lukidiversityapps.pricechangeaudit.domain.model.pricechange.ProductPriceChanged

class SubmitPriceChangeHandler(
    private val publishPriceChangeEventPort: PublishPriceChangeEventPort,
    private val currentTimePort: CurrentTimePort,
) : SubmitPriceChangeUseCase {
    override fun handle(command: SubmitPriceChangeCommand) {
        publishPriceChangeEventPort.publish(
            ProductPriceChanged(
                eventId = EventId.random(),
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
