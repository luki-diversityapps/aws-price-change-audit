package io.github.lukidiversityapps.pricechangeaudit.infrastructure.time

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class SystemCurrentTimeAdapterTest {
    @Test
    fun `should return current time`() {
        val currentTime = SystemCurrentTimeAdapter().now()
        assertNotNull(currentTime)
    }
}
