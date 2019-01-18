package com.senacor.cap.service.message

import org.springframework.data.repository.CrudRepository

@org.springframework.stereotype.Repository
interface ChatMessageRepository : CrudRepository<ChatMessage,Long> {

    //fun findByChannelId(channelId:String):List<ChatMessage>;
    fun findByChannelIdOrderByCreationTimestampDesc(s: String): List<ChatMessage>

}