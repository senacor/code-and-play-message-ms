package com.senacor.cap.service.message

import org.springframework.stereotype.Service

@Service
class ChatMessageService(private val channelService: ChannelService, private val chatMessageRepository: ChatMessageRepository ) {
    fun loadChatMessages(channelId:String): List<ChatMessage> {
        if(!channelService.existsChannel(channelId)){
            throw ChannelNotFoundException()
        }
        val result = chatMessageRepository.findByChannelIdOrderByCreationTimestampDesc(channelId)
        return result
    }

    fun saveChatMessage(channelId: String, sender: String, message: String): ChatMessage{
        Metrics.saveTotal.increment()
        if(!channelService.existsChannel(channelId)){
            throw ChannelNotFoundException()
        }
        val chatMessage = ChatMessage(channelId, sender, message)
        val result = chatMessageRepository.save(chatMessage)
        return result
    }

    fun deleteChatMessage(channelId: String, chatMessage: ChatMessage){
        if(!channelService.existsChannel(channelId)){
            throw ChannelNotFoundException()
        }
        chatMessageRepository.delete(chatMessage)
    }
}
