package com.senacor.cap.service.message

import java.time.Instant
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ChatMessage(var channelId: String,
                       var sender: String,
                       var message: String,
                       val creationTimestamp: Instant = Instant.now(),
                       @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0) {
}