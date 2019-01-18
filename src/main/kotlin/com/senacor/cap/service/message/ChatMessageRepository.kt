package com.senacor.cap.service.message

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatMessageRepository : CrudRepository<ChatMessage, Long> {
    fun findAllByChannelId(channelId: String): List<ChatMessage>
    fun findByChannelIdOrderByCreationTimestampDesc(channelId: String): List<ChatMessage>
}