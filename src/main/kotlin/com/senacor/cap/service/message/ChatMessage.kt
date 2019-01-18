package com.senacor.cap.service.message

import org.hibernate.annotations.CreationTimestamp
import java.time.Instant
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity

data class ChatMessage(

        var channelId: String?,
        var sender: String?,
        var message: String?,
        var creationTimestamp: Instant = Instant.now(),
        @Id
        @GeneratedValue
        var id: Long = 0
)