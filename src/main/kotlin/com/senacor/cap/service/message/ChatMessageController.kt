package com.senacor.cap.service.message

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
class ChatMessageController (val service: ChatMessageService) {

    @GetMapping(path = ["api/channels/{channelId}/messages"])
    fun loadChatMessages(@PathVariable channelId: String): List <ChatMessage>{
        val result = service.loadChatMessages(channelId)
        Metrics.incrementGetRequests()
        return result
    }

    @PostMapping(path = arrayOf("api/channels/{channelId}/messages"))
    fun newChatMessages(@PathVariable channelId: String, @RequestBody body: ChatMessage): ResponseEntity<ChatMessage>{
        val result = service.saveChatMessage(channelId, body.sender, body.message)
        Metrics.incrementPostRequests()
        return ResponseEntity.created(URI("/api/channels/$channelId/messages/${result.id}")).build()
    }

}