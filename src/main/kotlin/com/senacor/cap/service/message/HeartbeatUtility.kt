package com.senacor.cap.service.message

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForObject

@Component
class HeartbeatUtility(val env: Environment) {
    private val log: Logger = LoggerFactory.getLogger(Application::class.java)

    @Scheduled(fixedDelay = 30000)
    fun heartbeat() {


        val restTemplate = RestTemplate()
        val sb = StringBuilder()

        val channel = env.getProperty("SERVICE_CHANNEL")
        val host = env.getProperty("MESSAGE_MS_" + channel!!.toUpperCase() + "_SERVICE_HOST")
        val port = env.getProperty("MESSAGE_MS_" + channel.toUpperCase() + "_SERVICE_PORT")
        val channelMsHost = env.getProperty("CHANNEL_MS_SERVICE_HOST")
        val channelMsPort = env.getProperty("CHANNEL_MS_SERVICE_PORT")

        log.info("Registering channel $channel with endpoint $host:$port")
        log.debug("Host address is $host:$port")

        sb.append("{")
        sb.append("\"name\":\"$channel\",")
        sb.append("\"endpoint\":\"http://$host:$port\"")
        sb.append("}")


        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val request = HttpEntity(sb.toString(), headers)

        restTemplate.postForObject<String>("http://$channelMsHost:$channelMsPort/api/channels", request)

    }
}