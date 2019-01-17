package com.senacor.cap.service.message

import org.springframework.stereotype.Service


@Service
class ChatMessageService(
    private val channelService: ChannelService,
    private var chatMessageRepository: ChatMessageRepository
) {

    fun loadChatMessages(channelId: String): List<ChatMessage> {
        if (!channelService.existsChannel(channelId)) {
            throw ChannelNotFoundException()
        }
        return chatMessageRepository.findByChannelIdOrderByCreationTimestampDesc(channelId)
    }

    fun saveChatMessage(channelId: String, sender: String, message: String): ChatMessage {
        if (!channelService.existsChannel(channelId)) {
            throw ChannelNotFoundException()
        }
        val savedMessage = chatMessageRepository.save(ChatMessage(channelId, sender, message))
        Metrics.savedMessages.count()
        return savedMessage
    }
}