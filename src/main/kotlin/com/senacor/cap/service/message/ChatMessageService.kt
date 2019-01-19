package com.senacor.cap.service.message

import org.springframework.stereotype.Service

@Service
class ChatMessageService (val service: ChannelService, val repository: ChatMessageRepository){

    fun existsChannel(channel: String): Boolean {
        return this.service.existsChannel(channel)
    }

    fun loadChatMessages(channel: String): List<ChatMessage> {
        if (! this.existsChannel(channel)) {
            throw ChannelNotFoundException()
        }
        return this.repository.findByChannelIdOrderByCreationTimestampDesc(channel)
    }

    fun saveChatMessage(channelId: String, sender: String, message: String): ChatMessage {
        val chatMessage = ChatMessage(channelId, sender, message)
        repository.save(chatMessage)
        //Metrics.incrementSavedMessages()
        return chatMessage

    }
}