package com.senacor.cap.service.message

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
class ChatMessageController(var service: ChatMessageService) {

    @PostMapping(value = ["/api/channels/{channelId}/messages"])
    fun newChatMessage(@RequestBody chatMessage: ChatMessage, @PathVariable channelId: String): ResponseEntity<URI> {
        Metrics.messageCreateCounter.increment()
        Metrics.apiCounterPost.increment()
        chatMessage.channel = channelId
        val result = service.saveChatMessage(chatMessage)
        return ResponseEntity.created(URI.create("/api/channels/" + chatMessage.channel + "/messages/" + result.getId())).build()
    }

    @GetMapping(value = ["/api/channels/{channelId}/messages"])
    fun loadChatMessages(@PathVariable channelId: String): ResponseEntity<Iterable<ChatMessage>> {
        Metrics.messageLoadCounter.increment()
        Metrics.apiCounterGet.increment()
        val chatMessages = service.loadChatMessages(channelId)
        if (chatMessages.size == 0) return ResponseEntity.notFound().build()
        return ResponseEntity.ok(chatMessages)
    }

    @DeleteMapping(value = ["/api/channels/{channelId}/messages/{messageId}"])
    fun deleteMessageOfChannel(@PathVariable channelId: String, @PathVariable messageId: Long): ResponseEntity<String> {
        Metrics.messageDeleteCounter.increment()
        Metrics.apiCounterDelete.increment()
        val result = service.deleteById(messageId)
        if (result) return ResponseEntity.ok("Message deleted")
        return ResponseEntity.notFound().build()
    }
}