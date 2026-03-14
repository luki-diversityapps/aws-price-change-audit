package io.github.lukidiversityapps.pricechangeaudit.domain.model.common

@JvmInline
value class UserId private constructor(
    val value: String,
) {
    companion object {
        fun of(value: String): UserId {
            val normalized = value.trim()
            require(normalized.isNotBlank()) { "User cannot be blank" }
            return UserId(normalized)
        }
    }

    override fun toString(): String = value
}
