package com.senacor.cap.service.message.repository

import java.time.Instant
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Created by tali on 18.01.19.
 */
@Entity
data class ChatMessage(var channelId: String,
                       var sender: String,
                       var message: String,
                       var creationTimestamp: Instant = Instant.now(),
                       @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = 0)