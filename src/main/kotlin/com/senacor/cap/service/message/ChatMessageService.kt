package com.senacor.cap.service.message

import org.springframework.stereotype.Service

@Service
class ChatMessageService(private val channelService: ChannelService, private val chatMessageRepository: ChatMessageRepository){

    fun loadChatMessages(s: String): List<ChatMessage> {

        if(channelService.existsChannel(s)){
            return chatMessageRepository.findByChannelIdOrderByCreationTimestampDesc(s);

        }else{
            throw ChannelNotFoundException()
        }

    }

    fun saveChatMessage(channelID: String, sender: String, chatMessage: String): ChatMessage {
        return chatMessageRepository.save(ChatMessage(channelID, sender, chatMessage))
    }

}