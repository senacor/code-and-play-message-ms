package com.senacor.cap.service.message

import org.springframework.stereotype.Service

@Service
class ChatMessageService(var chatMessageRepository: ChatMessageRepository) : IChatMessageService {
    override fun deleteById(id: Long): Boolean {
        if (chatMessageRepository.findById(id) != null) {
            chatMessageRepository.deleteById(id)
            return true
        }
        return false
    }

    override fun saveChatMessages(chatMessages: List<ChatMessage>): Iterable<ChatMessage> {
        return chatMessageRepository.saveAll(chatMessages)
    }

    fun saveChatMessage(chatMessage: ChatMessage): ChatMessage {
        return chatMessageRepository.save(chatMessage)
    }

    override fun saveChatMessage(channel: String, email: String, message: String): ChatMessage {
        return saveChatMessage(ChatMessage(channel, email, message))
    }

    override fun loadChatMessages(channel: String): List<ChatMessage> {
        return chatMessageRepository.findAllByChannelOrderByMessageDesc(channel)
    }
}