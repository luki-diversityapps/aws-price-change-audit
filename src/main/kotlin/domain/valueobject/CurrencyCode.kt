package domain.valueobject

import java.util.Currency
import java.util.Locale

@JvmInline
value class CurrencyCode private constructor(
    val value: String,
) {
    companion object {
        fun of(value: String): CurrencyCode {
            val normalized = value.trim().uppercase(Locale.ROOT)

            require(isValidIso4217(normalized)) { "Invalid ISO-4217 currency code: $value" }

            return CurrencyCode(normalized)
        }

        private fun isValidIso4217(normalized: String): Boolean =
            runCatching { Currency.getInstance(normalized) }.isSuccess
    }

    override fun toString(): String = value
}
