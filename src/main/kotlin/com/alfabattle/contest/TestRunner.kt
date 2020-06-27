package com.alfabattle.contest

import com.alfabattle.contest.config.LoggerDelegate
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class TestRunner(private val kafkaTemplate: KafkaTemplate<String?, String?>) : ApplicationRunner {

    private val log by LoggerDelegate()

    @Value("\${spring.kafka.template.default-topic}")
    lateinit var defaultTopic: String

    override fun run(args: ApplicationArguments?) {
        kafkaTemplate.send(defaultTopic, randomString()).addCallback({ result ->
            log.info("Sent to kafka: {}", result)
        }, { failure ->
            log.error("Kafka error: {}", failure)
        })
    }

    private fun randomString(): String = (1..10)
            .map { Random.nextInt(0, listOf("a", "b", "c", "d").size) }
            .joinToString("", transform = listOf("a", "b", "c", "d")::get)
}
