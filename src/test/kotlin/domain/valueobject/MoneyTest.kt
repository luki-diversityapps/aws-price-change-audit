package domain.valueobject

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class MoneyTest {
    @Test
    fun `should create money`() {
        val money = Money.of(BigDecimal("100.00"), CurrencyCode("PLN"))

        assertNotNull(money)
        assertEquals(BigDecimal("100.00"), money.amount)
        assertEquals(CurrencyCode("PLN"), money.currency)
    }

    @Test
    fun `should not create money with negative amount`() {
        assertThrows(IllegalArgumentException::class.java) {
            Money.of(BigDecimal("-100"), CurrencyCode("PLN"))
        }
    }

    @Test
    fun `should not create money with zero amount`() {
        assertThrows(IllegalArgumentException::class.java) {
            Money.of(BigDecimal("0.0"), CurrencyCode("PLN"))
        }
    }

    @Test
    fun `should not create money with more than 2 decimal places`() {
        assertThrows(IllegalArgumentException::class.java) {
            Money.of(BigDecimal("100.000"), CurrencyCode("PLN"))
        }
    }

    @Test
    fun `should not create money with invalid currency code`() {
        assertThrows(IllegalArgumentException::class.java) {
            Money.of(BigDecimal("100"), CurrencyCode("INVALID"))
        }
    }

    @Test
    fun `should format money correctly`() {
        val money = Money.of(BigDecimal("123.45"), CurrencyCode("USD"))
        assertEquals("123.45 USD", money.toString())
    }
}
