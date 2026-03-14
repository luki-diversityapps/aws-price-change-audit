package io.github.lukidiversityapps.pricechangeaudit.infrastructure.time

import io.github.lukidiversityapps.pricechangeaudit.application.port.outbound.time.CurrentTimePort
import java.time.Instant

class SystemCurrentTimeAdapter : CurrentTimePort {
    override fun now(): Instant = Instant.now()
}
