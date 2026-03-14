package io.github.lukidiversityapps.pricechangeaudit.domain.model.pricechange

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PriceChangeReasonTest {
    @Test
    fun `should create valid reason`() {
        val reason = PriceChangeReason.of("Some reason")
        assertNotNull(reason)
    }

    @Test
    fun `should not create reason with blank value`() {
        assertThrows(IllegalArgumentException::class.java) {
            PriceChangeReason.of(" ")
        }
    }
}
