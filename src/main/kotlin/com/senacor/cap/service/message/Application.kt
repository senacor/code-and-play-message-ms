package com.senacor.cap.service.message

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import java.sql.Timestamp

@SpringBootApplication
@EnableScheduling
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

class ChatMessage (channelId: String, sender: String, message: String, creationTimestamp: Timestamp, id:Int)