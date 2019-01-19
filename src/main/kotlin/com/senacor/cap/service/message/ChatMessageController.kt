package com.senacor.cap.service.message

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ChatMessageController(private val serviceMock: ChatMessageService) {


    @GetMapping("/api/channels/{channelID}/messages")
    fun loadChatMessages(@PathVariable("channelID") channelID: String): List<ChatMessage> {
        Metrics.apiCallCounterGet.increment()
        return serviceMock.loadChatMessages(channelID)
    }

    @PostMapping("/api/channels/{channelID}/messages")
    fun newChatMessages(@PathVariable("channelID")channelID: String, @RequestBody chatMessage: ChatMessage): ResponseEntity<ChatMessage> {
        Metrics.apiCallCounterPost.increment()
        val headers = HttpHeaders()
        val saved: ChatMessage = serviceMock.saveChatMessage(channelID, chatMessage.sender!!, chatMessage.message!!)
        headers.set(HttpHeaders.LOCATION, "/api/channels/${saved.channelId}/messages/${saved.id}")
        return ResponseEntity(saved, headers,HttpStatus.CREATED)

    }
}

