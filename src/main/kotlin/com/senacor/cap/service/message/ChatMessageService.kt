package com.senacor.cap.service.message

import org.springframework.stereotype.Service

@Service
class ChatMessageService (
        val channelService: ChannelService,
        val chatMessageRepository: ChatMessageRepository
) {
    fun loadChatMessages(channelId: String): List<ChatMessage> {
        checkChannel(channelId)
        return chatMessageRepository.findByChannelIdOrderByCreationTimestampDesc(channelId)

    }

    fun saveChatMessage(channelId: String, sender: String, message: String): ChatMessage {
        Metrics.messages_saved_total.increment()
        checkChannel(channelId)
        return chatMessageRepository.save(ChatMessage(channelId, sender, message))
    }

    fun checkChannel(channelId: String) {
        if (!channelService.existsChannel(channelId)) {
            throw ChannelNotFoundException()
        }
    }
}