package com.senacor.cap.service.message

import org.springframework.stereotype.Service

@Service
class ChatMessageService (val channelService: ChannelService, val chatMessageRepository : ChatMessageRepository )
{
    fun loadChatMessages(channelId: String): List<ChatMessage> {
        if ( channelService.existsChannel(channelId))

      return  chatMessageRepository.findByChannelIdOrderByCreationTimestampDesc(channelId)

        else throw ChannelNotFoundException()
    }
}