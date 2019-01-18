package com.senacor.cap.service.message

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
class ChatMessageController(var service: ChatMessageService) {

    @PostMapping(value = ["/api/messages"])
    fun newChatMessage(@RequestBody chatMessage: ChatMessage): ResponseEntity<URI> {
        val result = service.saveChatMessage(chatMessage)
        return ResponseEntity.created(URI.create("/api/channels/" + chatMessage.channel + "/messages/" + result.getId())).build()
    }

    @GetMapping(value = ["/api/channels/{channelId}/messages"])
    fun loadChatMessages(@PathVariable channelId: String): ResponseEntity<Iterable<ChatMessage>> {
        val chatMessages = service.loadChatMessages(channelId)
        if (chatMessages.size == 0) return ResponseEntity.notFound().build()
        return ResponseEntity.ok(chatMessages)
    }

    @DeleteMapping(value = ["/api/channels/{channelId}/messages/{messageId}"])
    fun deleteMessageOfChannel(@PathVariable channelId: String, @PathVariable messageId: Long): ResponseEntity<String> {
        val result = service.deleteById(messageId)
        if (result) return ResponseEntity.ok("Message deleted")
        return ResponseEntity.notFound().build()
    }
}