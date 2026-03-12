package application.submit

import domain.valueobject.Money
import domain.valueobject.ProductId

data class SubmitPriceChangeCommand(
    val productId: ProductId,
    val oldPrice: Money,
    val newPrice: Money,
    val changedBy: String,
    val reason: String?,
) {
    init {
        require(oldPrice.currency == newPrice.currency) {
            "Price change must be in the same currency"
        }
        require(oldPrice.amount != newPrice.amount) {
            "Price change must be different"
        }
        require(changedBy.isNotBlank()) { "ChangedBy cannot be blank" }
    }
}
