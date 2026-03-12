package domain.valueobject

@JvmInline
value class ProductId(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { "ProductId cannot be blank" }
    }
}
