package com.senacor.cap.service.message


import com.sun.jndi.toolkit.url.Uri
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponents
import java.net.URI
import java.util.*

@RestController
class ChatMessageController(val service: ChatMessageService) {

    @GetMapping("/api/channels/dev/messages/")
    fun loadChatMessages(s: String): List<ChatMessage> {
        return service.loadChatMessages(s)
    }

    @PostMapping("/api/channels/dev/messages/{channelId}")
    fun newChatMessage(@PathVariable channelId: String, @RequestBody chatMessage: ChatMessage): ResponseEntity<ChatMessage> {
        var saveChatMessage = service.saveChatMessage(channelId, chatMessage.sender, chatMessage.message)

        return ResponseEntity.created(URI.create("/api/channels/dev/messages/"+saveChatMessage.id)).build();

    }

}
