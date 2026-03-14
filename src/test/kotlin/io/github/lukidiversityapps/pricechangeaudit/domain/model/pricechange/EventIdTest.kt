package io.github.lukidiversityapps.pricechangeaudit.domain.model.pricechange

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class EventIdTest {
    @Test
    fun `should create valid EventId`() {
        val eventId = EventId.random()
        Assertions.assertNotNull(eventId)
    }

    @Test
    fun `should create EventId from valid string`() {
        val eventId = EventId.of("  valid-event-id  ")
        Assertions.assertNotNull(eventId)
    }
}
