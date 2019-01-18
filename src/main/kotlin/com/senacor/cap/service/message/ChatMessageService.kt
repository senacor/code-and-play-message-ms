package com.senacor.cap.service.message

import org.springframework.stereotype.Service
import java.time.Instant

@Service
class ChatMessageService(val channelService: ChannelService,
        val repository: ChatMessageRepository) {

    fun saveChatMessage(message: ChatMessage) = repository.save(message)

    fun saveChatMessage(channel: String, sender: String, message: String): ChatMessage {
        return repository.save(ChatMessage(channel, sender, message, Instant.now()))
    }

    fun loadChatMessages(channel: String): List<ChatMessage> {
        if(channelService.existsChannel(channel)) {
            return repository.findByChannelIdOrderByCreationTimestampDesc(channel)
        }

        throw ChannelNotFoundException()
    }
}