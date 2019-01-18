package com.senacor.cap.service.message

import org.springframework.data.repository.CrudRepository
import java.util.*

interface ChatMessageRepository: CrudRepository<ChatMessage, Long> {

    fun findByChannelIdOrderByCreationTimestampDesc(channelId: String): List<ChatMessage>

}
