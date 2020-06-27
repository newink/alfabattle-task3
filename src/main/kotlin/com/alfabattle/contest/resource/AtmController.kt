package com.alfabattle.contest.resource

import com.alfabattle.contest.alfa.JSONResponseBankATMDetails
import com.alfabattle.contest.config.LoggerDelegate
import org.apache.http.impl.client.HttpClients
import org.apache.http.ssl.SSLContexts
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.security.KeyStore
import javax.net.ssl.SSLContext


@RestController
class AtmController {

    val log by LoggerDelegate()

    @Value("\${alfabattle.atm-api-host}")
    lateinit var apiHost: String

    @Value("\${alfabattle.client-secret}")
    lateinit var clientSecret: String

    @Value("\${alfabattle.client-id}")
    lateinit var clientId: String

    @GetMapping("/atms/nearest")
    fun getNearestAtm(@RequestParam latitude: String,
                      @RequestParam longitude: String,
                      @RequestParam(defaultValue = "false") payments: Boolean) {

        val httpEntity = httpEntity()
        val response = getRestTemplate()!!.exchange("${apiHost}/atms", HttpMethod.GET, httpEntity, JSONResponseBankATMDetails::class.java)
        log.info("{}", response)
    }

    @GetMapping("/atms/nearest-with-alfik")
    fun getNearestWithAlfik(@RequestParam latitude: String,
                            @RequestParam longitude: String,
                            @RequestParam alfik: Int) {

    }

    @GetMapping("/atms/{id}")
    fun getById(@PathVariable id: String) {

    }

    private fun httpEntity(): HttpEntity<Any> {
        val headers = mapOf("x-ibm-client-id" to clientId)
        return HttpEntity<Any>(headers)
    }

    @Throws(Exception::class)
    private fun getRestTemplate(): RestTemplate? {
        val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
        val password = "password".toCharArray()
        val jksInputStream = AtmController::class.java.classLoader.getResourceAsStream("alfabattle.jks")
        keyStore.load(jksInputStream, password)
        val sslContext: SSLContext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, password)
                .build()
        val httpClient = HttpClients.custom()
                .setSSLContext(sslContext)
                .build()
        val requestFactory = HttpComponentsClientHttpRequestFactory()
        requestFactory.httpClient = httpClient
        return RestTemplate(requestFactory)
    }
}
