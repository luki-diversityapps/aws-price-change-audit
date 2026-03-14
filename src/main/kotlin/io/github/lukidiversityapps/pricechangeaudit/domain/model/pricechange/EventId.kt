package io.github.lukidiversityapps.pricechangeaudit.domain.model.pricechange

import java.util.UUID

@JvmInline
value class EventId private constructor(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { "EventId cannot be blank" }
    }

    companion object {
        fun of(value: String) = EventId(value.trim())

        fun random() = EventId(UUID.randomUUID().toString())
    }

    override fun toString(): String = value
}
