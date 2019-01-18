package com.senacor.cap.service.message

interface IChatMessageService {

    fun loadChatMessages(channel: String): List<ChatMessage>

    fun saveChatMessage(channel: String, email: String, message: String): ChatMessage

    fun saveChatMessages(chatMessages: List<ChatMessage>): Iterable<ChatMessage>

    fun deleteById(id: Long): Boolean
}