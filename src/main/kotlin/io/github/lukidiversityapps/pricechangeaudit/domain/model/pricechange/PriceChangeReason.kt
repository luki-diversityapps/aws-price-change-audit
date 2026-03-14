package io.github.lukidiversityapps.pricechangeaudit.domain.model.pricechange

@JvmInline
value class PriceChangeReason private constructor(
    val value: String,
) {
    companion object {
        fun of(value: String): PriceChangeReason {
            val normalized = value.trim()
            require(normalized.isNotBlank()) { "PriceChangeReason cannot be blank" }
            return PriceChangeReason(normalized)
        }
    }

    override fun toString(): String = value
}
