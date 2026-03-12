package port.submit

import application.submit.SubmitPriceChangeCommand

fun interface SubmitPriceChangeUseCase {
    fun handle(command: SubmitPriceChangeCommand)
}
