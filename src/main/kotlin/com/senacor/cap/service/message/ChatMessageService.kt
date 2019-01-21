package com.senacor.cap.service.message

import org.springframework.stereotype.Service

@Service
class ChatMessageService(private val channelService: ChannelService,private val chatMessageRepository: ChatMessageRepository)

 {
//    fun existsChannel(channelId: String) = true
    fun loadChatMessages(channelId: String): List<ChatMessage> {
        if (!channelService.existsChannel(channelId)){
            throw ChannelNotFoundException()
        }
        return chatMessageRepository.findByChannelIdOrderByCreationTimestampDesc(channelId)
    }

    fun saveChatMessage(channelId : String, sender: String, message: String): ChatMessage{

        if (!channelService.existsChannel(channelId)){
            throw ChannelNotFoundException()
        }
        Metrics. message_ms_messages_saved_total .increment()
        return chatMessageRepository.save(ChatMessage(channelId, sender, message))



    }
}