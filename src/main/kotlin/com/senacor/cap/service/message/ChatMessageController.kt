package com.senacor.cap.service.message

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/channels/{channelId}/messages")
class ChatMessageController(cms : ChatMessageService) {

    private val chatMessageService = cms

    @GetMapping
    fun loadChatMessages(@PathVariable(value="channelId") channelId : String) : List<ChatMessage>{
        return chatMessageService.loadChatMessages(channelId)
    }


    @PostMapping
    fun newChatMessage(@PathVariable(value="channelId") channelId : String, @RequestBody cm : ChatMessage) : ResponseEntity<ChatMessage>{
        val newChatMessage : ChatMessage = chatMessageService.saveChatMessage(channelId, cm.sender, cm.message)
        return  ResponseEntity.created(URI("/api/channels/"+channelId+"/messages/"+newChatMessage.id)).build()
    }
}