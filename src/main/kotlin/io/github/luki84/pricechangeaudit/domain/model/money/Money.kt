package io.github.luki84.pricechangeaudit.domain.model.money

import java.math.BigDecimal
import java.math.RoundingMode

class Money private constructor(
    val amount: BigDecimal,
    val currency: CurrencyCode,
) {
    init {
        require(amount >= BigDecimal.ZERO) { "Money amount must be non-negative" }
    }

    companion object {
        private const val SCALE = 2

        fun of(
            amount: BigDecimal,
            currency: CurrencyCode,
        ): Money = Money(amount.setScale(SCALE, RoundingMode.HALF_UP), currency)
    }

    override fun toString(): String = "$amount $currency"

    override fun equals(other: Any?): Boolean =
        other is Money &&
            amount == other.amount &&
            currency == other.currency

    override fun hashCode(): Int = 31 * amount.hashCode() + currency.hashCode()
}
