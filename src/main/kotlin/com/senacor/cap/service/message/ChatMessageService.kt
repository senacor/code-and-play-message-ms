package com.senacor.cap.service.message

import org.springframework.stereotype.Service

@Service
class ChatMessageService(
    private var chatMessageRepository: ChatMessageRepository
) {

    fun loadChatMessages(channelId: String): List<ChatMessage> {
        return chatMessageRepository.findAll().toList()
    }

    fun saveChatMessage(channelId: String, sender: String, message: String): ChatMessage {
        return chatMessageRepository.save(ChatMessage(channelId, sender, message))
    }
}