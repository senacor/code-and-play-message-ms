package com.senacor.cap.service.message

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI



@RestController
class ChatMessageController(val service: ChatMessageService) {


    @GetMapping("/api/channels/{channelId}/messages")
    fun loadChatMessages(@PathVariable("channelId") channelId: String): List<ChatMessage>{
        Metrics.requestGetTotal.increment()
        val result = service.loadChatMessages(channelId)
        return result
    }

    @PostMapping("/api/channels/{channelId}/messages")
    fun newChatMessage(@PathVariable("channelId") channelId: String, @RequestBody chatMessage: ChatMessage): ResponseEntity<ChatMessage>{
        Metrics.requestPostTotal.increment()
        val result = service.saveChatMessage(channelId, chatMessage.sender, chatMessage.message)

        return ResponseEntity.created(URI.create("/api/channels/${channelId}/messages/${result.id}")).build()
    }

    @DeleteMapping("/api/channels/{channelId}/messages/{messageId}")
    fun deleteChatMessage(@PathVariable("channelId") channelId: String, @PathVariable("messageId") messageId: Long, @RequestBody chatMessage: ChatMessage){
        service.deleteChatMessage(channelId, chatMessage)
    }
}