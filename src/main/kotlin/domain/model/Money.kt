package domain.model

import java.math.BigDecimal

data class Money (
    val amount: BigDecimal,
    val currency: CurrencyCode
) {
    init {
        require(amount >= BigDecimal.ZERO) { "Money amount must be non-negative" }
    }
}
