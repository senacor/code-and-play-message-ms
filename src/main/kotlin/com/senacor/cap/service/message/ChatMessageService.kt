package com.senacor.cap.service.message

import org.springframework.stereotype.Service

@Service
class ChatMessageService(
        private val channelServiceMock: ChannelService,
        private val chatMessageRepository: ChatMessageRepository
)
{


    fun loadChatMessages(s: String): List<ChatMessage> {
        if(! channelServiceMock.existsChannel(s)){
            throw ChannelNotFoundException()
        }

        return chatMessageRepository.findByChannelIdOrderByCreationTimestampDesc(s)
    }

    fun saveChatMessage(channelId: String, sender: String, message: String): ChatMessage {

        Metrics.messages_saved_total.increment()

        if(!channelServiceMock.existsChannel(channelId)) {
            throw ChannelNotFoundException()
        }

        return chatMessageRepository.save(ChatMessage(channelId, sender, message))
    }

}