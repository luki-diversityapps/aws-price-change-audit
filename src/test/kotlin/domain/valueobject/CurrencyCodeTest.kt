package domain.valueobject

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CurrencyCodeTest {
    @Test
    fun `should create currency code`() {
        val currencyCode = CurrencyCode.of("PLN")
        assertEquals("PLN", currencyCode.value)
    }

    @Test
    fun `should not create currency code with invalid code`() {
        assertThrows(IllegalArgumentException::class.java) {
            CurrencyCode.of("INVALID")
        }
    }

    @Test
    fun `should format currency code correctly with lowercase`() {
        val currencyCode = CurrencyCode.of("usd")
        assertEquals("USD", currencyCode.toString())
    }
}
