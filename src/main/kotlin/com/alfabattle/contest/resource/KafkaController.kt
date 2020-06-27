package com.alfabattle.contest.resource

import com.alfabattle.contest.config.LoggerDelegate
import com.alfabattle.contest.model.Payment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

//@RestController
class KafkaController {

    private val log by LoggerDelegate()

    private val list: MutableList<Payment> = mutableListOf()

//    @KafkaListener(
//            topics = ["RAW_PAYMENTS"],
//            containerFactory = "kafkaListenerContainerFactory")
//    fun paymentsListener(@Payload payment: Payment) {
//        log.info("Got message: {}", payment)
//        list.add(payment)
//    }

    @GetMapping("/admin/health")
    fun health(): ResponseEntity<Map<String, String>> {
        return ResponseEntity.ok(mapOf("status" to "UP"))
    }

    @GetMapping("/analytic")
    fun getAnalytics(): ResponseEntity<Collection<Analytics>> = ResponseEntity.ok(calculateStats().values)

    @GetMapping("/analytic/{userId}")
    fun getByUserId(@PathVariable userId: String): ResponseEntity<Any> {
        val analytics = calculateStats()[userId]
        return if (analytics != null) ResponseEntity.ok(analytics) else userNotFound()
    }

    @GetMapping("/analytic/{userId}/templates")
    fun template(@PathVariable userId: String): ResponseEntity<Any> {
        val analytics = calculateStats()[userId]
        if (analytics == null) {
            return userNotFound()
        }

        val templates = list.filter{ it.userId == userId}.groupingBy { p -> Template(p.recipientId, p.categoryId, p.amount) }.eachCount().filter { it.value >= 3 }.map { it.key }
        return ResponseEntity.ok(templates)
    }

    private fun userNotFound(): ResponseEntity<Any> =
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(mapOf("status" to "user not found"))

    @GetMapping("/analytic/{userId}/stats")
    fun statsByUser(@PathVariable userId: String): ResponseEntity<Any> {
        val analytics = calculateStats()[userId]
        if (analytics == null) {
            return userNotFound()
        }

        val oftenCategory = analytics.analyticInfo.keys.groupingBy { it }.eachCount().maxBy { it.value }!!.key
        val rareCategory = analytics.analyticInfo.keys.groupingBy { it }.eachCount().minBy { it.value }!!.key
        val maxBy = analytics.analyticInfo.entries.maxBy { entry -> entry.value["sum"]!! }!!.key
        val minBy = analytics.analyticInfo.entries.minBy { entry -> entry.value["sum"]!! }!!.key
        return ResponseEntity.ok(mapOf(
                "oftenCategoryId" to oftenCategory,
                "rareCategoryId" to rareCategory,
                "maxAmountCategoryId" to maxBy,
                "minAmountCategoryId" to minBy
        ))
    }

    private fun calculateStats(): Map<String, Analytics> {
        return list.map { payment ->
            val byCategory = list.filter { p -> payment.userId == p.userId }
                    .groupBy(Payment::categoryId, Payment::amount)
            val stats = mutableMapOf<String, Map<String, BigDecimal>>()
            byCategory.forEach { (key, list) ->

                val max = list.reduce { acc, bigDecimal -> acc.max(bigDecimal) }
                val min = list.reduce { acc, bigDecimal -> acc.min(bigDecimal) }
                val sum = list.reduce { acc, bigDecimal -> acc.add(bigDecimal) }

                val calculated = mapOf("min" to min, "max" to max, "sum" to sum)
                stats[key.toString()] = calculated
            }

            val userSum = list.filter { p -> payment.userId == p.userId }.map(Payment::amount).reduce { acc, bigDecimal -> acc.add(bigDecimal) }
            return@map payment.userId to Analytics(payment.userId, userSum, stats)
        }.toMap()
    }

    data class Analytics(val userId: String,
                         val totalSum: BigDecimal,
                         val analyticInfo: Map<String, Map<String, BigDecimal>>)

    data class Template(val recipientId: String,
                        val categoryId: Int,
                        val amount: BigDecimal)
}
