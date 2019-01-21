package com.senacor.cap.service.message

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatMessageRepository : CrudRepository<ChatMessage, Long> {
    fun findByChannelIdOrderByCreationTimestampDesc(s: String): List<ChatMessage>
}