package com.senacor.cap.service.message

import org.hibernate.annotations.CreationTimestamp
import org.springframework.beans.factory.annotation.Autowired
import java.time.Instant
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ChatMessage(val channelId: String?, val sender: String, val message: String,
                       val creationTimestamp: Instant=Instant.now(),
                       @Id @GeneratedValue
                       val id: Long?=null)

