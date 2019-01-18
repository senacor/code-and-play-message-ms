package com.senacor.cap.service.message.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Created by tali on 18.01.19.
 */
@Repository
interface ChatMessageRepository : CrudRepository<ChatMessage, Long> {
    fun findBySender(sender: String): List<ChatMessage>
    fun findByChannelId(channelId: String): List<ChatMessage>
    fun findByChannelIdOrderByCreationTimestampDesc(s: String): List<ChatMessage>
}