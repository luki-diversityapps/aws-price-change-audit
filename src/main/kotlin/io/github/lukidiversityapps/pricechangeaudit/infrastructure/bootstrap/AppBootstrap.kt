package io.github.lukidiversityapps.pricechangeaudit.infrastructure.bootstrap

import io.github.lukidiversityapps.pricechangeaudit.application.port.inbound.SubmitPriceChangeUseCase
import io.github.lukidiversityapps.pricechangeaudit.application.port.outbound.pricechange.PriceChangeEventPublisher
import io.github.lukidiversityapps.pricechangeaudit.application.pricechange.submit.SubmitPriceChangeHandler
import io.github.lukidiversityapps.pricechangeaudit.infrastructure.configuration.SqsConfig
import io.github.lukidiversityapps.pricechangeaudit.infrastructure.sqs.SqsPriceChangeEventPublisher
import io.github.lukidiversityapps.pricechangeaudit.infrastructure.time.SystemCurrentTimeAdapter
import software.amazon.awssdk.services.sqs.SqsClient
import tools.jackson.databind.ObjectMapper
import tools.jackson.module.kotlin.jacksonObjectMapper

object AppBootstrap {
    private val objectMapper: ObjectMapper by lazy { jacksonObjectMapper() }

    private val sqsClient: SqsClient by lazy { SqsClient.create() }

    private val sqsConfig =
        SqsConfig(
            queueUrl = System.getenv("SQS_QUEUE_URL") ?: error("SQS_QUEUE_URL not set"),
        )

    fun priceChangeEventPublisher(): PriceChangeEventPublisher =
        SqsPriceChangeEventPublisher(sqsClient, objectMapper, sqsConfig.queueUrl)

    fun submitPriceChangeUseCase(): SubmitPriceChangeUseCase =
        SubmitPriceChangeHandler(
            priceChangeEventPublisher(),
            SystemCurrentTimeAdapter(),
        )
}
