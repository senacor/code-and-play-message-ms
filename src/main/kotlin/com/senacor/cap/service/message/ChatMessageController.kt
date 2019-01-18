package com.senacor.cap.service.message

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.RestController

@RestController
class ChatMessageController(private val serviceMock: ChatMessageService) {
    fun loadChatMessages(channelID: String): List<ChatMessage> {
        return serviceMock.loadChatMessages(channelID)
    }

    fun newChatMessages(channelID: String, chatMessage: ChatMessage): ResponseEntity<ChatMessage> {
        val headers: HttpHeaders = HttpHeaders()
        val saved: ChatMessage = serviceMock.saveChatMessage(channelID, chatMessage.sender, chatMessage.message)
        headers.set(HttpHeaders.LOCATION, "/api/channels/dev/messages/${saved.id}")
        return ResponseEntity(saved, headers,HttpStatus.CREATED)

    }
}

