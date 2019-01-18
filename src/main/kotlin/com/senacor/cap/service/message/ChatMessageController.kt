package com.senacor.cap.service.message

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
class ChatMessageController(val service: ChatMessageService) {

    @GetMapping("/api/channels/{channelId}/messages")
    fun loadChatMessages(@PathVariable("channelId") channelId: String): List<ChatMessage> {
        val msg = service.loadChatMessages(channelId)
        return (msg)
    }

    @PostMapping("/api/channels/{channelId}/messages")
    fun newChatMessages(@PathVariable("channelId") channelId: String, @RequestBody message: ChatMessage): ResponseEntity<ChatMessage> {
        message.channelId = channelId
        val msg =  service.saveChatMessage(channelId, message.sender, message.message)
        return ResponseEntity.created(URI("/api/channels/$channelId/messages/${msg.id}")).body(msg)
    }
}