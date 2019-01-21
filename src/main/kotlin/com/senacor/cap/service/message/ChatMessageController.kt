package com.senacor.cap.service.message

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

const val CHAT_MESSAGES_PATH = "/api/channels/{channelId}/messages"

@RestController
@RequestMapping(CHAT_MESSAGES_PATH)
class ChatMessageController(
        private val serviceMock: ChatMessageService) {

    @GetMapping
    fun loadChatMessages(@PathVariable("channelId") channelId: String): List<ChatMessage> {

        return serviceMock.loadChatMessages(channelId)

    }

    @PostMapping
    fun newChatMessage(@PathVariable("channelId") channelId: String, @RequestBody chatMessage: ChatMessage): ResponseEntity<ChatMessage> {
        val newChatMessage: ChatMessage = serviceMock.saveChatMessage(channelId, chatMessage.sender, chatMessage.message)

        val location: UriComponents = UriComponentsBuilder.newInstance().path(CHAT_MESSAGES_PATH).pathSegment(newChatMessage.id!!.toString()).buildAndExpand(channelId)

        return ResponseEntity.created(location.toUri()).build()

    }


}
