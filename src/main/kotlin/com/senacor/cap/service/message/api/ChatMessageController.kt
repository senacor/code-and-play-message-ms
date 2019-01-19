package com.senacor.cap.service.message.api

import com.senacor.cap.service.message.Metrics
import com.senacor.cap.service.message.repository.ChatMessage
import com.senacor.cap.service.message.service.ChatMessageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

/**
 * Created by tali on 18.01.19.
 */

@RestController
class ChatMessageController(val chatMessageService: ChatMessageService)
{
    @GetMapping("/api/channels/{channelId}/messages")
    fun loadChatMessages(@PathVariable channelId: String): List<ChatMessage> {
        Metrics.countRequestGetTotal.increment()
        return chatMessageService.loadChatMessages(channelId)
    }

    @PostMapping("/api/channels/{channelId}/messages")
    fun newChatMessages(@PathVariable channelId: String?, @RequestBody chatMessage: ChatMessage): ResponseEntity<ChatMessage> {
        Metrics.countMsgSavedTotal.increment()
        Metrics.countRequestPostTotal.increment()
        val saveChatMessage = chatMessageService.saveChatMessage(channelId, chatMessage.sender, chatMessage.message)
        val id = saveChatMessage.id;
        return ResponseEntity.created(URI("/api/channels/$channelId/messages/$id")).body(saveChatMessage)
    }

}