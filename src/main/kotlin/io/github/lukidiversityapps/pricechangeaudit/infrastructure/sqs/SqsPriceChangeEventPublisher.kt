package io.github.lukidiversityapps.pricechangeaudit.infrastructure.sqs

import io.github.lukidiversityapps.pricechangeaudit.application.port.outbound.pricechange.PriceChangeEventPublisher
import io.github.lukidiversityapps.pricechangeaudit.domain.model.pricechange.ProductPriceChanged
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.SendMessageRequest
import tools.jackson.databind.ObjectMapper

class SqsPriceChangeEventPublisher(
    private val sqsClient: SqsClient,
    private val objectMapper: ObjectMapper,
    private val queueUrl: String,
) : PriceChangeEventPublisher {
    override fun publish(event: ProductPriceChanged) {
        val message = objectMapper.writeValueAsString(event)

        val request =
            SendMessageRequest
                .builder()
                .queueUrl(queueUrl)
                .messageBody(message)
                .build()

        sqsClient.sendMessage(request)
    }
}
