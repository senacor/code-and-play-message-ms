package com.senacor.cap.service.message

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForObject
import java.lang.StringBuilder
import java.net.InetAddress

@SpringBootApplication
class Application {

    private val log: Logger = LoggerFactory.getLogger(Application::class.java)

    @Bean
    fun registerChannel(env: Environment): CommandLineRunner {

        return CommandLineRunner {
            val restTemplate = RestTemplate()
            val sb = StringBuilder()

            val channel = env.getProperty("SERVICE_CHANNEL")
            val host = env.getProperty("MESSAGE_MS_" + channel!!.toUpperCase() + "SERVICE_HOST")
            val port = env.getProperty("MESSAGE_MS_" + channel!!.toUpperCase() + "SERVICE_PORT")

            log.info("Registering channel $channel with endpoint $host:$port")
            log.debug("Host address is $host:$port")

            sb.append("{")
            sb.append("\"name\":\"$channel\",")
            sb.append("\"endpoint\":\"http://$host:$port\"")
            sb.append("}")


            val headers = HttpHeaders()
            headers.contentType = MediaType.APPLICATION_JSON

            val request = HttpEntity(sb.toString(), headers)

            restTemplate.postForObject<String>("http://channel-ms:8080/api/channels", request)
        }
    }

}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

