package com.senacor.cap.service.message.repositories

import com.senacor.cap.service.message.models.ChatMessage
import org.springframework.data.jpa.repository.JpaRepository

interface ChatMessageRepository: JpaRepository<ChatMessage, Long> {

    fun findByChannelIdOrderByCreationTimestampDesc(channelId: String): List<ChatMessage>

}