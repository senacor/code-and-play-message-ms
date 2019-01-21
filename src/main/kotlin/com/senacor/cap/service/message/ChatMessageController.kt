package com.senacor.cap.service.message

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
class ChatMessageController ( val service: ChatMessageService) {

    @GetMapping("/api/channels/{channelId}/messages")
    fun loadChatMessages(@PathVariable("channelId")channelId: String): List<ChatMessage>{
        val result = service.loadChatMessages(channelId)
        return result
    }

    @PostMapping("/api/channels/{channel}/messages")
    fun newChatMessage(@PathVariable("channel")channelId: String, @RequestBody message: ChatMessage ): ResponseEntity<ChatMessage>{
        val result = service.saveChatMessage(channelId, message.sender, message.message)
        return ResponseEntity.created(URI.create("/api/channels/${channelId}/messages/${result.id}")).build()
    }

}