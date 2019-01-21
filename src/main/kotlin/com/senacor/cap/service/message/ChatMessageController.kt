package com.senacor.cap.service.message


import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
class ChatMessageController(val service: ChatMessageService) {

    @GetMapping("/api/channels/{channelId}/messages")
    fun loadChatMessages(@PathVariable channelId: String): List<ChatMessage> {
        return service.loadChatMessages(channelId)
    }

    @PostMapping("/api/channels/{channelId}/messages")
    fun newChatMessage(@PathVariable channelId: String, @RequestBody chatMessage: ChatMessage): ResponseEntity<ChatMessage> {
        val saveChatMessage = service.saveChatMessage(channelId, chatMessage.sender, chatMessage.message)
        return ResponseEntity.created(URI.create("/api/channels/"+channelId+"/messages/"+saveChatMessage.id)).build()
    }

    @DeleteMapping("/api/channels/{channelId}/messages/{id}")
    fun deleteChatMessage(@PathVariable channelId: String, @PathVariable id:Long) {
        service.deleteChatMessage(id)
    }

}
