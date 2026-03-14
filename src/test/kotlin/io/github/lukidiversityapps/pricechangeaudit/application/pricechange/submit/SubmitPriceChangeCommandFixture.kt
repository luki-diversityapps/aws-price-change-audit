package io.github.lukidiversityapps.pricechangeaudit.application.pricechange.submit

import io.github.lukidiversityapps.pricechangeaudit.domain.model.common.UserId
import io.github.lukidiversityapps.pricechangeaudit.domain.model.money.CurrencyCode
import io.github.lukidiversityapps.pricechangeaudit.domain.model.money.Money
import io.github.lukidiversityapps.pricechangeaudit.domain.model.pricechange.PriceChangeReason
import io.github.lukidiversityapps.pricechangeaudit.domain.model.pricechange.ProductId
import java.math.BigDecimal

object SubmitPriceChangeCommandFixture {
    fun createSubmitPriceChangeCommand(
        productId: ProductId = ProductId("123"),
        oldPrice: Money = Money.of(BigDecimal("100.00"), CurrencyCode.of("PLN")),
        newPrice: Money = Money.of(BigDecimal("150.00"), CurrencyCode.of("PLN")),
        changedBy: UserId = UserId.of("user"),
        reason: PriceChangeReason? = PriceChangeReason.of("reason"),
    ): SubmitPriceChangeCommand =
        SubmitPriceChangeCommand(
            productId = productId,
            oldPrice = oldPrice,
            newPrice = newPrice,
            changedBy = changedBy,
            reason = reason,
        )
}
