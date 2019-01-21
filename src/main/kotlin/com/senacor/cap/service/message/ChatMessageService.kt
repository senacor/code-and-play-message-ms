package com.senacor.cap.service.message

import org.springframework.stereotype.Service

@Service
class ChatMessageService(val channelService: ChannelService, val chatMessageRepository: ChatMessageRepository) {
    fun loadChatMessages(channelId: String): List<ChatMessage> {
        if (!channelService.existsChannel(channelId)) {
            throw ChannelNotFoundException()
        }
        return chatMessageRepository.findByChannelIdOrderByCreationTimestampDesc(channelId)
    }

    fun saveChatMessage(channel: String, sender: String, message: String): ChatMessage {
        return chatMessageRepository.save(ChatMessage(channel,sender,message))
    }

}
