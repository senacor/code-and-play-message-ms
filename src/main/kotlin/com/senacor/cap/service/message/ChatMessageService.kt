package com.senacor.cap.service.message

import org.springframework.stereotype.Service

@Service
class ChatMessageService(
        val channelServiceMock: ChannelService,
        val chatMessageRepository: ChatMessageRepository
) {
    fun loadChatMessages(s: String):  List<ChatMessage> {
        return chatMessageRepository.findByChannelIdOrderByCreationTimestampDesc(s)
    }

    fun saveChatMessage(chanelId: String?, sender: String, message: String): ChatMessage {
        Metrics.savedTotal.increment()
        return chatMessageRepository.save(ChatMessage(chanelId, sender, message))
    }
}

