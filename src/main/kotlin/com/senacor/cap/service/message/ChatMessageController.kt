package com.senacor.cap.service.message

import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.time.Instant


@RestController
class ChatMessageController(val serviceMock: ChatMessageService) {

    @GetMapping("/api/channels/{channelId}/messages")
    fun loadChatMessages(@PathVariable channelId: String) : List<ChatMessage>{
        return serviceMock.loadChatMessages(channelId)
    }

    @PostMapping("/api/channels/{channel}/messages")
    fun newChatMessages(@PathVariable channel: String, @RequestBody chatMessage: ChatMessage): ResponseEntity<ChatMessage> {
        var saveChatMessage = serviceMock.saveChatMessage(chatMessage.channelId, chatMessage.sender, chatMessage.message)
        val id=saveChatMessage.id
        return ResponseEntity.created(URI("/api/channels/$channel/messages/$id")).body(saveChatMessage)
    }
}
