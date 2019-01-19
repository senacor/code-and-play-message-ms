package com.senacor.cap.service.message.controller

import com.senacor.cap.service.message.models.ChatMessage
import com.senacor.cap.service.message.services.ChatMessageService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpHeaders



@RestController
@RequestMapping("/api/channels/{channelId}/messages")
class ChatMessageController(val chatMessageService: ChatMessageService){

    @GetMapping
    fun loadChatMessages (@PathVariable(value="channelId") channelId: String): List<ChatMessage> {
        return chatMessageService.loadChatMessages(channelId)
    }

    @PostMapping
    fun newChatMessages (@PathVariable(value="channelId") channelId: String, chatMessage: ChatMessage): ResponseEntity<ChatMessage>{

        val newMessage: ChatMessage = chatMessageService.saveChatMessage(chatMessage.channelId, chatMessage.sender, chatMessage.message)
        val headers = HttpHeaders()
        headers.add(HttpHeaders.LOCATION, "/api/channels/dev/messages/${newMessage.id}")

        return ResponseEntity(newMessage, headers, HttpStatus.CREATED)
    }

}