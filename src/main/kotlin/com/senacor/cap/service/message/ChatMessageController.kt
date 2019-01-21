package com.senacor.cap.service.message

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder

const val CHAT_MESSAGE_PATH = "/api/channels/{channelId}/messages"

@RestController
@RequestMapping(CHAT_MESSAGE_PATH)
class ChatMessageController(var chatMessageService: ChatMessageService) {

    @GetMapping
    fun loadChatMessages(@PathVariable("channelId") channelId: String): List<ChatMessage> {
        return chatMessageService.loadChatMessages(channelId)
    }

    @PostMapping
    fun newChatMessage(@PathVariable("channelId") channel: String, @RequestBody chatMessage: ChatMessage): ResponseEntity<Void> {
        val newChatMessage: ChatMessage = chatMessageService.saveChatMessage(channel, chatMessage.sender, chatMessage.message)

        val location:UriComponents = UriComponentsBuilder.newInstance().path(CHAT_MESSAGE_PATH).pathSegment(newChatMessage.id.toString()).buildAndExpand(channel)

        return ResponseEntity.created(location.toUri()).build()
    }
}