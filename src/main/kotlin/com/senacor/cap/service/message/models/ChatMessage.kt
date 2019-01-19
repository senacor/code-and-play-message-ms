package com.senacor.cap.service.message.models

import java.time.Instant
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ChatMessage(val channelId: String, val sender: String, val message: String, var creationTimestamp: Instant = Instant.now(), @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
var id: Long = 0) {


}