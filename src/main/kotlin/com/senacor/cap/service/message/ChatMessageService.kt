package com.senacor.cap.service.message

import org.springframework.stereotype.Service

@Service
class ChatMessageService(
        private val channelService: ChannelService,
        private val chatMessageRepository: ChatMessageRepository
) {

    fun loadChatMessages(channelId: String): List<ChatMessage> {
        if ( channelService.existsChannel(channelId) ) {
            return chatMessageRepository.findByChannelIdOrderByCreationTimestampDesc(channelId)
        } else {
            throw ChannelNotFoundException()
        }
    }


    fun saveChatMessage(channelId: String, sender: String, chatMessage: String): ChatMessage {
        return chatMessageRepository.save(ChatMessage(channelId, sender, chatMessage))
    }

}