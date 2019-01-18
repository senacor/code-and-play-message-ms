package com.senacor.cap.service.message

import org.springframework.data.repository.CrudRepository

interface ChatMessageRepository : CrudRepository<ChatMessage, Long> {
//    fun findAllByOrderByAddedAtDesc(): Iterable<ChatMessage>

    fun findByChannelIdOrderByCreationTimestampDesc(channelId: String): List<ChatMessage>

}