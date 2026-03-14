package io.github.lukidiversityapps.pricechangeaudit.application.pricechange.submit

import io.github.lukidiversityapps.pricechangeaudit.application.port.outbound.pricechange.PublishPriceChangeEventPort
import io.github.lukidiversityapps.pricechangeaudit.application.port.outbound.time.CurrentTimePort
import io.github.lukidiversityapps.pricechangeaudit.domain.model.common.UserId
import io.github.lukidiversityapps.pricechangeaudit.domain.model.money.CurrencyCode
import io.github.lukidiversityapps.pricechangeaudit.domain.model.money.Money
import io.github.lukidiversityapps.pricechangeaudit.domain.model.pricechange.PriceChangeReason
import io.github.lukidiversityapps.pricechangeaudit.domain.model.pricechange.PriceChanged
import io.github.lukidiversityapps.pricechangeaudit.domain.model.pricechange.ProductId
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.Instant

class SubmitPriceChangeHandlerTest {
    @Test
    fun `should publish price changed event built from command`() {
        val publishedEvents = mutableListOf<PriceChanged>()
        val publishPriceChangeEventPort =
            PublishPriceChangeEventPort { event ->
                publishedEvents += event
            }
        val currentTime = Instant.parse("2026-03-14T10:15:30Z")
        val currentTimePort = CurrentTimePort { currentTime }
        val handler =
            SubmitPriceChangeHandler(
                publishPriceChangeEventPort = publishPriceChangeEventPort,
                currentTimePort = currentTimePort,
            )
        val command =
            SubmitPriceChangeCommand(
                productId = ProductId("product-123"),
                oldPrice = Money.of(BigDecimal("100.00"), CurrencyCode.of("PLN")),
                newPrice = Money.of(BigDecimal("150.00"), CurrencyCode.of("PLN")),
                changedBy = UserId.of("user-123"),
                reason = PriceChangeReason.of("seasonal promotion"),
            )

        handler.handle(command)

        assertEquals(1, publishedEvents.size)

        val event = publishedEvents.single()
        assertNotNull(event.eventId)
        assertFalse(event.eventId.value.isBlank())
        assertEquals(PriceChanged.VERSION, event.eventVersion)
        assertEquals(currentTime, event.occurredAt)
        assertEquals(command.productId, event.productId)
        assertEquals(command.oldPrice, event.oldPrice)
        assertEquals(command.newPrice, event.newPrice)
        assertEquals(command.changedBy, event.changedBy)
        assertEquals(command.reason, event.reason)
    }

    @Test
    fun `should publish price changed event with null reason`() {
        val publishedEvents = mutableListOf<PriceChanged>()
        val publishPriceChangeEventPort =
            PublishPriceChangeEventPort { event ->
                publishedEvents += event
            }
        val currentTime = Instant.parse("2026-03-14T10:15:30Z")
        val currentTimePort = CurrentTimePort { currentTime }
        val handler =
            SubmitPriceChangeHandler(
                publishPriceChangeEventPort = publishPriceChangeEventPort,
                currentTimePort = currentTimePort,
            )
        val command =
            SubmitPriceChangeCommand(
                productId = ProductId("product-123"),
                oldPrice = Money.of(BigDecimal("100.00"), CurrencyCode.of("PLN")),
                newPrice = Money.of(BigDecimal("150.00"), CurrencyCode.of("PLN")),
                changedBy = UserId.of("user-123"),
                reason = null,
            )

        handler.handle(command)

        val event = publishedEvents.single()
        assertEquals(null, event.reason)
    }
}
