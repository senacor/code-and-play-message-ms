package com.senacor.cap.service.message.service

import com.senacor.cap.service.message.ChannelNotFoundException
import com.senacor.cap.service.message.repository.ChatMessage
import com.senacor.cap.service.message.repository.ChatMessageRepository
import org.springframework.stereotype.Service

/**
 * Created by tali on 18.01.19.
 */
@Service
class ChatMessageService(val channelService: ChannelService,
                         val chatMessageRepository: ChatMessageRepository)
{
    fun loadChatMessages(s: String): List<ChatMessage> {
        if(channelService.existsChannel(s)){
            return chatMessageRepository.findByChannelIdOrderByCreationTimestampDesc(s)
        } else {
            throw ChannelNotFoundException()
        }
    }

    fun saveChatMessage(channelId: String?,
                        sender: String,
                        message: String): ChatMessage {
        return chatMessageRepository.save(ChatMessage(channelId, sender, message))
    }

}