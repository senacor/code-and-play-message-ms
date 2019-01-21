package com.senacor.cap.service.message

import org.springframework.stereotype.Service

@Service
class ChatMessageService(cs : ChannelService, cmr : ChatMessageRepository) {

    var channelService = cs
    var chatMessageRepository = cmr

    fun loadChatMessages(channelId : String) : List<ChatMessage>{
        if (channelService.existsChannel(channelId)){
            return chatMessageRepository.findByChannelIdOrderByCreationTimestampDesc(channelId)
        }else{
            throw ChannelNotFoundException()
        }
    }

    fun saveChatMessage(channelId : String, sender : String, message : String) : ChatMessage{
        if(channelService.existsChannel(channelId)){
            return chatMessageRepository.save(ChatMessage(channelId, sender, message))
        }else{
            throw ChannelNotFoundException()
        }

    }
}