package com.alfabattle.contest

import com.alfabattle.contest.config.LoggerDelegate
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.env.Environment
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories
import org.springframework.kafka.annotation.EnableKafka
import java.net.InetAddress
import java.net.UnknownHostException

@SpringBootApplication
@EnableKafka
@EnableElasticsearchRepositories
class ContestApplication


fun main(args: Array<String>) {
    val context = runApplication<ContestApplication>(*args)
    logApplicationStartup(context.environment)
}

private fun logApplicationStartup(env: Environment) {
    val log = LoggerFactory.getLogger(ContestApplication::class.java)

    var protocol = "http"
    if (env.getProperty("server.ssl.key-store") != null) {
        protocol = "https"
    }
    val serverPort = env.getProperty("server.port")
    var contextPath = env.getProperty("server.servlet.context-path")
    if (contextPath.isNullOrBlank()) {
        contextPath = "/"
    }
    var hostAddress: String? = "localhost"
    try {
        hostAddress = InetAddress.getLocalHost().hostAddress
    } catch (e: UnknownHostException) {
        log.warn("The host name could not be determined, using `localhost` as fallback")
    }
    log.info("""
               
                ----------------------------------------------------------
                    Application '{}' is running! Access URLs:
                    Local: 		{}://localhost:{}{}
                    External: 	{}://{}:{}{}
                ----------------------------------------------------------""".trimIndent(),
            env.getProperty("spring.application.name"),
            protocol,
            serverPort,
            contextPath,
            protocol,
            hostAddress,
            serverPort,
            contextPath)
}


