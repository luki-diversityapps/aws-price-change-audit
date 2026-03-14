package io.github.lukidiversityapps.pricechangeaudit.application.port.inbound

import io.github.lukidiversityapps.pricechangeaudit.application.pricechange.submit.SubmitPriceChangeCommand

fun interface SubmitPriceChangeUseCase {
    fun handle(command: SubmitPriceChangeCommand)
}
