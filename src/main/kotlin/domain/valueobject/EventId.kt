package domain.valueobject

import java.util.UUID

@JvmInline
value class EventId private constructor(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { "EventId cannot be blank" }
    }

    companion object {
        fun random() = EventId(UUID.randomUUID().toString())
    }
}
