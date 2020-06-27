package com.alfabattle.contest

import com.alfabattle.contest.model.Payment
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.apache.kafka.common.serialization.Deserializer
import java.math.BigDecimal

class ErrorSkippingDeserializer : Deserializer<Payment> {

    override fun configure(configs: Map<String?, *>?, isKey: Boolean) {}

    override fun deserialize(topic: String?, data: ByteArray?): Payment? {
        val mapper = ObjectMapper().findAndRegisterModules()
        var result: Payment? = null
        try {
            result = mapper.readValue(data, Payment::class.java)
        } catch (exception: Exception) {
            println("Error in deserializing bytes $exception")
        }
        return Payment("empty", "empty", "empty", 1, "empty", BigDecimal.ZERO)
    }

    override fun close() {}
}
