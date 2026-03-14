package io.github.lukidiversityapps.pricechangeaudit.domain.model.money

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class MoneyTest {
    @Test
    fun `should create money`() {
        val money = Money.of(BigDecimal("100.00"), CurrencyCode.of("PLN"))

        assertNotNull(money)
        assertEquals(BigDecimal("100.00"), money.amount)
        assertEquals(CurrencyCode.of("PLN"), money.currency)
    }

    @Test
    fun `should not create money with negative amount`() {
        assertThrows(IllegalArgumentException::class.java) {
            Money.of(BigDecimal("-100"), CurrencyCode.of("PLN"))
        }
    }

    @Test
    fun `should round money with more than 2 decimal places`() {
        val money = Money.of(BigDecimal("100.007"), CurrencyCode.of("PLN"))
        assertEquals(BigDecimal("100.01"), money.amount)
    }

    @Test
    fun `should not create money with invalid currency code`() {
        assertThrows(IllegalArgumentException::class.java) {
            Money.of(BigDecimal("100"), CurrencyCode.of("INVALID"))
        }
    }

    @Test
    fun `should format money correctly`() {
        val money = Money.of(BigDecimal("123.45"), CurrencyCode.of("USD"))
        assertEquals("123.45 USD", money.toString())
    }
}
