package io.github.lukidiversityapps.pricechangeaudit.domain.model.money

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CurrencyCodeTest {
    @Test
    fun `should create currency code`() {
        val currencyCode = CurrencyCode.Companion.of("PLN")
        Assertions.assertEquals("PLN", currencyCode.value)
    }

    @Test
    fun `should not create currency code with invalid code`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            CurrencyCode.Companion.of("INVALID")
        }
    }

    @Test
    fun `should format currency code correctly with lowercase`() {
        val currencyCode = CurrencyCode.Companion.of("usd")
        Assertions.assertEquals("USD", currencyCode.toString())
    }
}
