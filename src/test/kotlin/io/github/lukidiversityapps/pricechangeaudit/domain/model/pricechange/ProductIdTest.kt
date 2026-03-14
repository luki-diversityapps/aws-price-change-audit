package io.github.lukidiversityapps.pricechangeaudit.domain.model.pricechange

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ProductIdTest {
    @Test
    fun `should create valid ProductId`() {
        val productId = ProductId("123")
        Assertions.assertNotNull(productId)
    }

    @Test
    fun `should not create ProductId with blank value`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            ProductId(" ")
        }
    }
}
