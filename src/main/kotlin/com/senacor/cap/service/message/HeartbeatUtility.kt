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

data class Channel(val id: String, val endpoint: String)

@Component
class HeartbeatUtility(env: Environment) {
    private val log: Logger = LoggerFactory.getLogger(HeartbeatUtility::class.java)

    private val restTemplate = RestTemplate()

    private lateinit var channel: String
    private lateinit var host: String
    private lateinit var port: String
    private lateinit var channelMsHost: String
    private lateinit var channelMsPort: String

    init {
        channel = env.getProperty("SERVICE_CHANNEL")!!
        host = env.getProperty("MESSAGE_MS_" + channel.toUpperCase() + "_SERVICE_HOST")!!
        port = env.getProperty("MESSAGE_MS_" + channel.toUpperCase() + "_SERVICE_PORT")!!
        channelMsHost = env.getProperty("CHANNEL_MS_SERVICE_HOST")!!
        channelMsPort = env.getProperty("CHANNEL_MS_SERVICE_PORT")!!

        log.info("Registering channel $channel with endpoint $host:$port")
        log.debug("Host address is $host:$port")
    }

    @Scheduled(fixedDelay = 30000)
    fun heartbeat() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val request = HttpEntity(Channel(channel, "http://$host:$port"), headers)

        restTemplate.postForObject<String>("http://$channelMsHost:$channelMsPort/api/channels", request)
    }
}