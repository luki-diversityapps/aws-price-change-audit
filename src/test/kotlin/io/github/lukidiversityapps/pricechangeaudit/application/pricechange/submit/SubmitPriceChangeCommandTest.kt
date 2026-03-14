package io.github.lukidiversityapps.pricechangeaudit.application.pricechange.submit

import io.github.lukidiversityapps.pricechangeaudit.domain.model.common.UserId
import io.github.lukidiversityapps.pricechangeaudit.domain.model.money.CurrencyCode
import io.github.lukidiversityapps.pricechangeaudit.domain.model.money.Money
import io.github.lukidiversityapps.pricechangeaudit.domain.model.pricechange.PriceChangeReason
import io.github.lukidiversityapps.pricechangeaudit.domain.model.pricechange.ProductId
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class SubmitPriceChangeCommandTest {
    @Test
    fun `should create valid command`() {
        val command = createSubmitPriceChangeCommand()
        assertNotNull(command)
    }

    @Test
    fun `should create command without optional fields`() {
        val command = createSubmitPriceChangeCommand(reason = null)
        assertNotNull(command)
    }

    @Test
    fun `should throw exception if old price has different currency code than new price`() {
        assertThrows(IllegalArgumentException::class.java) {
            createSubmitPriceChangeCommand(
                oldPrice = Money.of(BigDecimal("100.00"), CurrencyCode.of("PLN")),
                newPrice = Money.of(BigDecimal("150.00"), CurrencyCode.of("USD")),
            )
        }
    }

    @Test
    fun `should throw exception if old price is equal to new price`() {
        assertThrows(IllegalArgumentException::class.java) {
            createSubmitPriceChangeCommand(
                oldPrice = Money.of(BigDecimal("100.00"), CurrencyCode.of("PLN")),
                newPrice = Money.of(BigDecimal("100.00"), CurrencyCode.of("PLN")),
            )
        }
    }

    private fun createSubmitPriceChangeCommand(
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
