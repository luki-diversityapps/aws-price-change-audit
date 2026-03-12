package domain.valueobject

@JvmInline
value class CurrencyCode(
    val value: String,
) {
    init {
        require(value.matches(Regex("[A-Z]{3}"))) {
            "Currency code must be ISO-4217"
        }
    }

    override fun toString(): String = value
}
