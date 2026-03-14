package io.github.lukidiversityapps.pricechangeaudit.domain.model.common

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UserIdTest {
    @Test
    fun `should create valid user id`() {
        val userId = UserId.of("123")
        assertNotNull(userId)
    }

    @Test
    fun `should not create user id with blank value`() {
        assertThrows(IllegalArgumentException::class.java) {
            UserId.of(" ")
        }
    }
}
