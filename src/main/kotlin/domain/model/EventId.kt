package domain.model

@JvmInline
value class EventId(val value: String) {
    init {
        require(value.isNotBlank()) { "EventId cannot be blank" }
    }
}
