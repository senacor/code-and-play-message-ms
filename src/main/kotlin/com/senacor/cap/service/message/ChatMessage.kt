package com.senacor.cap.service.message

import java.time.Instant
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
data class ChatMessage (

    var channelId: String? = null,
    var sender: String,
    var message: String,
    var creationTimestamp: Instant = Instant.now(),

    @Id
    @GeneratedValue
    var id: Long? = null

)