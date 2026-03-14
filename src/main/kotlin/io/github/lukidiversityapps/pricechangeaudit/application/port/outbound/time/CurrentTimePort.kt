package io.github.lukidiversityapps.pricechangeaudit.application.port.outbound.time

import java.time.Instant

interface CurrentTimePort {
    fun now(): Instant
}
