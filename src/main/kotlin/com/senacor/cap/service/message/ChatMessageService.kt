package com.senacor.cap.service.message

import org.springframework.stereotype.Service

@Service
class ChatMessageService( private val channelService : ChannelService,
                          private val chatMessageRepository: ChatMessageRepository) {
    fun loadChatMessages(channelId: String): List<ChatMessage> {
        if(!channelService.existsChannel(channelId)){
            throw ChannelNotFoundException()
        }
        return chatMessageRepository.findByChannelIdOrderByCreationTimestampDesc(channelId)
    }
    fun saveChatMessage(channelId: String, sender : String, message: String): ChatMessage{
        Metrics.saved_total.increment()
        if(!channelService.existsChannel(channelId)){
            throw ChannelNotFoundException()
        }
        return chatMessageRepository.save(ChatMessage(channelId, sender, message))
    }
}