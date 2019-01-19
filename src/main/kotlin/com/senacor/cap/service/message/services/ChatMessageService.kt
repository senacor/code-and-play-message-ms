package com.senacor.cap.service.message.services

import com.senacor.cap.service.message.ChannelNotFoundException
import com.senacor.cap.service.message.Metrics
import com.senacor.cap.service.message.models.ChatMessage
import com.senacor.cap.service.message.repositories.ChatMessageRepository
import org.springframework.stereotype.Service

@Service
class ChatMessageService(var channelService: ChannelService, var chatMessageRepository : ChatMessageRepository) {

    fun loadChatMessages(channelId: String): List<ChatMessage> {

        if (channelService.existsChannel(channelId)) {
            return chatMessageRepository.findByChannelIdOrderByCreationTimestampDesc(channelId)
        } else {
            throw ChannelNotFoundException()
        }

    }

    fun saveChatMessage(channelId: String, sender: String, message: String) : ChatMessage{

        Metrics.message_ms_messages_saved_total.increment()
        return chatMessageRepository.save(ChatMessage(channelId, sender, message))

    }

}