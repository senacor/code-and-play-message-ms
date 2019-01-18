package com.senacor.cap.service.message

import com.sun.deploy.net.HttpResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
class ChatMessageController(
        private val chatMessageService: ChatMessageService ) {

    @GetMapping("/api/channels/{channelId}/messages")
    fun loadChatMessages(@PathVariable( value="channelId") channelId: String): List<ChatMessage> {
        return chatMessageService.loadChatMessages( channelId )
    }

    @PostMapping("/api/channels/{channelId}/messages")
    fun newChatMessages(@PathVariable( value="channelId" ) channelId: String, @RequestBody chatMessage: ChatMessage): ResponseEntity<ChatMessage> {
        var rChatMessage: ChatMessage = chatMessageService.saveChatMessage(channelId, chatMessage.sender, chatMessage.message)
        var location: URI = URI("/api/channels/${rChatMessage.channelId}/messages/${rChatMessage.id}")
        return ResponseEntity.created(location).build()
    }

    //return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.LOCATION, "/api/channels/dev/messages/${rChatMessage.id}" ).body(rChatMessage)
    //return ResponseEntity.status(HttpStatus.OK).body(rChatMessage) ACCEPTED
    //return ResponseEntity.created(location).header(HttpHeaders.LOCATION, "/api/channels/${rChatMessage.channelId}/messages/${rChatMessage.id}" ).body(rChatMessage)

}