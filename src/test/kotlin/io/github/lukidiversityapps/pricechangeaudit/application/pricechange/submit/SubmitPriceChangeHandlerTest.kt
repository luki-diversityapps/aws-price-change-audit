package io.github.lukidiversityapps.pricechangeaudit.application.pricechange.submit

import io.github.lukidiversityapps.pricechangeaudit.application.port.outbound.pricechange.PublishPriceChangeEventPort
import io.github.lukidiversityapps.pricechangeaudit.application.port.outbound.time.CurrentTimePort
import io.github.lukidiversityapps.pricechangeaudit.application.pricechange.submit.SubmitPriceChangeCommandFixture.createSubmitPriceChangeCommand
import io.github.lukidiversityapps.pricechangeaudit.domain.model.pricechange.PriceChanged
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Instant

class SubmitPriceChangeHandlerTest {
    @Test
    fun `should publish PriceChanged event from command data`() {
        val publishedEvents = mutableListOf<PriceChanged>()
        val currentTime = Instant.parse("2026-03-14T10:15:30Z")
        val handler = createSubmitPriceChangeHandler(publishedEvents = publishedEvents, currentTime = currentTime)
        val command = createSubmitPriceChangeCommand()

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
    fun `should publish PriceChanged event with null reason when command reason is null`() {
        val publishedEvents = mutableListOf<PriceChanged>()
        val handler = createSubmitPriceChangeHandler(publishedEvents = publishedEvents)
        val command = createSubmitPriceChangeCommand(reason = null)

        handler.handle(command)

        val event = publishedEvents.single()
        assertNull(event.reason)
    }

    private fun createSubmitPriceChangeHandler(
        publishedEvents: MutableList<PriceChanged>,
        currentTime: Instant = Instant.parse("2026-03-14T10:15:30Z"),
    ): SubmitPriceChangeHandler {
        val publishPriceChangeEventPort =
            PublishPriceChangeEventPort { event ->
                publishedEvents += event
            }
        val currentTimePort = CurrentTimePort { currentTime }

        return SubmitPriceChangeHandler(
            publishPriceChangeEventPort = publishPriceChangeEventPort,
            currentTimePort = currentTimePort,
        )
    }
}
